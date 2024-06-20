package com.warehouse.admin.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Batch;
import com.warehouse.admin.entity.Client;
import com.warehouse.admin.entity.Inventory;
import com.warehouse.admin.entity.Storage;
import com.warehouse.admin.exception.ClientNotExistException;
import com.warehouse.admin.exception.InventoryNotExistException;
import com.warehouse.admin.exception.InventoryNotExistException;
import com.warehouse.admin.exception.StorageNotExistException;
import com.warehouse.admin.exception.WareHouseNotFoundByIdException;
import com.warehouse.admin.mapper.InventoryMapper;
import com.warehouse.admin.repository.BatchRepo;
import com.warehouse.admin.repository.ClientRepo;
import com.warehouse.admin.repository.InventoryRepo;
import com.warehouse.admin.repository.StorageRepo;
import com.warehouse.admin.requestdto.InventoryRequest;
import com.warehouse.admin.responsedto.InventoryResponse;
import com.warehouse.admin.service.InventoryService;
import com.warehouse.admin.utility.ResponseStructure;
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
	        Batch batch   = batchRepo.findById(batchId).orElseThrow(() -> new BatchNotExistException("BatchId : " + batchId + ", is not exist"));
	        return storageRepo.findById(storageId).map(storage -> {
	            Inventory inventory = inventoryMapper.mapInventoryToInventoryRequest(inventoryRequest, new Inventory());
	            inventory.setRestockedAt(LocalDate.now());
                
	            double productSize = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
	            double updatedStorageArea = storage.getAvailableArea() - (productSize * batch.getQuantity());
	            storage.setAvailableArea(updatedStorageArea);

	            double updatedStorageMaxWeight = storage.getMaxAdditionalWeight() - (inventory.getWeightInKg() * batch.getQuantity());
	            storage.setMaxAdditionalWeight(clientId);

	            inventory.setClient (client);
	            inventory.setBatch(batch);
	            storage = storageRepo.save(storage);
	            inventory.setStorages(List.of(storage));

	            inventory = inventoryRepo.save(inventory);

	            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
	                    .setStatus(HttpStatus.CREATED.value())
	                    .setMessage("Inventory Created")
	                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
	        }).orElseThrow(() -> new StorageNotExistException("StorageId : " + storageId + ", is not exist"));
	    }

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			long productId) {
		 return inventoryRepo.findById(productId).map(inventory -> {
	            inventory = inventoryMapper.mapInventoryToInventoryRequest(inventoryRequest, inventory);

	            List<Storage> listStorages = getUpdatedStorages(inventory);
	            inventory.setStorages(listStorages);
	            inventory = inventoryRepo.save(inventory);
	            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
	                    .setStatus(HttpStatus.CREATED.value())
	                    .setMessage("Inventory Updated")
	                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
	        }).orElseThrow(() -> new InventoryNotExistException("ProductId : " + productId + ", is not exist"));
	}

	 private static List<Storage> getUpdatedStorages(Inventory inventory) {
	        double productSize = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
	        double qnt = inventory.getQuantity();

	        double maxWeight = inventory.getWeightInKg() * inventory.getQuantity();

	        List<Storage> listStorages = inventory.getStorages();
	        listStorages.forEach(storage -> {
	            double updatedStorageArea = storage.getAvailableArea() - (productSize * qnt);
	            storage.setAvailableArea(updatedStorageArea);

	            double updatedStorageMaxWeight = storage.getMaxAdditionalWeight() - maxWeight;
	            storage.setMaxAdditionalWeight(maxWeight);
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
	
	

	
		
//		return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<InventoryResponse>()
//				.setStatus(HttpStatus.CREATED.value())
//				.setMessage("Inventory created")
//				.setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
//		}).orElseThrow(()->new InventoryNotExistException("StorageId not found"));

	

}
