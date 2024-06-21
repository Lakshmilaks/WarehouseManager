package com.warehouse.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.entity.Batch;
import com.warehouse.entity.Client;
import com.warehouse.entity.Inventory;
import com.warehouse.entity.Storage;
import com.warehouse.enums.MaterialTypes;
import com.warehouse.exception.ClientNotExistException;
import com.warehouse.exception.IllegalOperationException;
import com.warehouse.exception.InventoryNotExistException;
import com.warehouse.exception.StorageNotExistException;
import com.warehouse.exception.WareHouseNotFoundByIdException;
import com.warehouse.mapper.InventoryMapper;
import com.warehouse.repository.BatchRepo;
import com.warehouse.repository.ClientRepo;
import com.warehouse.repository.InventoryRepo;
import com.warehouse.repository.StorageRepo;
import com.warehouse.requestdto.InventoryRequest;
import com.warehouse.responsedto.InventoryResponse;
import com.warehouse.service.InventoryService;
import com.warehouse.utility.ResponseStructure;
@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private InventoryRepo inventoryRepo;

	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private StorageRepo storageRepo;
	
	@Autowired
	private ClientRepo  clientRepo;
	
	@Autowired
	private BatchRepo batchRepo;

	

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,
			long storageId, long clientId,int quantity) {
	
	        Client client = clientRepo.findById(clientId).orElseThrow(() -> new ClientNotExistException("ClientId : " + clientId + ", is not exist"));
	        Storage storage= storageRepo.findById(storageId).orElseThrow(()-> new StorageNotExistException("StorageId not found!!!"));
	        
	            Inventory inventory = inventoryMapper.mapInventoryToInventoryRequest(inventoryRequest, new Inventory());
	            inventory.setRestockedAt(LocalDate.now());
                
	            double productSize = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
	            double updatedStorageArea = storage.getAvailableArea() - (productSize * quantity );
	            
	            if(updatedStorageArea<=0) 
	            	throw new IllegalOperationException("Insufficient Storage!!!!");
	            else
	            	storage.setAvailableArea(updatedStorageArea);
	            
	            
	            double updatedStorageMaxWeight = storage.getMaxAdditionalWeight() - (inventory.getWeightInKg() * quantity);
	            
	            if(updatedStorageMaxWeight<=0)
	            	throw new IllegalOperationException("weight is too much not supported by storage");
	            else
	            storage.setMaxAdditionalWeight(updatedStorageMaxWeight);

	            List<MaterialTypes> inventoryMaterialTypes =inventory.getMaterialTypes();
	            List<MaterialTypes> storageMaterialTypes =storage.getMaterialTypes();
	            
	            if(!new HashSet<>(storageMaterialTypes).containsAll(storageMaterialTypes))
	            	throw new IllegalOperationException("MaterialTypes not matched with storage materials");
	            
	            inventory.setClient (client);
	            storage.setSellerId(inventory.getSellerId());
	            storage = storageRepo.save(storage);
	            inventory.setStorages(List.of(storage));

	            inventory = inventoryRepo.save(inventory);
	            Batch batch = new Batch();
	            batch.setQuantity(quantity);
	            batch.setStorage(storage);
	            batch.setInventory(inventory);
	            batch = batchRepo.save(batch);
	            
	            

	       return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
	                    .setStatus(HttpStatus.CREATED.value())
	                    .setMessage("Inventory Created")
	                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory,batch)));
	}

	

	 private static List<Storage> getUpdatedStorages(Inventory inventory, InventoryRequest inventoryRequest) {
		 double requestProductSize = inventoryRequest.getBreadthInMeters() * inventoryRequest.getHeightInMeters() * inventoryRequest.getLengthInMeters();
	        double productSize = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
	        
	        double qnt = inventory.getBatchs().getFirst().getQuantity();
	        
	        double reqMaxWeight = inventoryRequest.getWeightInKg() * qnt;
	        double availableMaxWeight = inventory.getWeightInKg() * qnt;


	        List<Storage> listStorages = inventory.getStorages();
	        listStorages.forEach(storage -> {
	            double updatedStorageArea = storage.getAvailableArea() - ((requestProductSize - productSize) * qnt);
	            
	            if (updatedStorageArea <= 0)
                    throw new IllegalOperationException("Sufficient space in storage");
                else
                    storage.setAvailableArea(updatedStorageArea);
	            
	            List<MaterialTypes> inventoryMaterialTypes = inventory.getMaterialTypes();
                List<MaterialTypes> storageMaterialTypes = storage.getMaterialTypes();
                if (!new HashSet<>(storageMaterialTypes).containsAll(inventoryMaterialTypes))
                    throw new IllegalOperationException("Material types are not match with storage materials");
	            
	        });
	        return listStorages;
	    }
	 
	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(long productId) {
		 return inventoryRepo.findById(productId).map(inventory -> {
	            return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<InventoryResponse>()
	                    .setStatus(HttpStatus.FOUND.value())
	                    .setMessage("Inventory Founded")
	                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
	        }).orElseThrow(() -> new InventoryNotExistException("productId : " + productId + ", is not exist"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories() {
		 List<InventoryResponse> inventoryResponses = inventoryRepo
	                .findAll()
	                .stream()
	                .map(inventory -> inventoryMapper.mapInventoryToInventoryResponse(inventory))
	                .toList();
	        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<InventoryResponse>>()
	                .setStatus(HttpStatus.FOUND.value())
	                .setMessage("Inventories are Founded")
	                .setData(inventoryResponses));
	}



	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			long productId) {
		 return inventoryRepo.findById(productId).map(inventory -> {
	            List<Storage> listStorages = getUpdatedStorages(inventory, inventoryRequest);
	            inventory = inventoryMapper.mapInventoryToInventoryRequest(inventoryRequest, inventory);
	            inventory.setRestockedAt(LocalDate.now());

	            inventory.setStorages(listStorages);
	            inventory = inventoryRepo.save(inventory);
	            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
	                    .setStatus(HttpStatus.CREATED.value())
	                    .setMessage("Inventory Updated")
	                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
	        }).orElseThrow(() -> new InventoryNotExistException("ProductId : " + productId + ", is not exist"));
	}
	
	
}
