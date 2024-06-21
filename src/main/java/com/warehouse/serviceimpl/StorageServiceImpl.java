package com.warehouse.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.entity.Storage;
import com.warehouse.entity.StorageType;
import com.warehouse.entity.Warehouse;
import com.warehouse.enums.AdminType;
import com.warehouse.exception.StorageNotExistException;
import com.warehouse.exception.StorageTypeNotExistException;
import com.warehouse.exception.WareHouseNotFoundByIdException;
import com.warehouse.mapper.StorageMapper;
import com.warehouse.repository.StorageRepo;
import com.warehouse.repository.StorageTypeRepo;
import com.warehouse.repository.WarehouseRepo;
import com.warehouse.requestdto.StorageRequest;
import com.warehouse.responsedto.AddressResponse;
import com.warehouse.responsedto.AdminResponse;
import com.warehouse.responsedto.StorageResponse;
import com.warehouse.service.StorageService;
import com.warehouse.utility.ResponseStructure;
@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	private StorageRepo storageRepo;

	@Autowired
	private StorageMapper storageMapper;

	@Autowired
	private WarehouseRepo warehouseRepo;

	@Autowired
	private StorageTypeRepo storageTypeRepo;

	@Override
	public ResponseEntity<ResponseStructure<String>> createStorage(StorageRequest storageRequest, long warehouseId,
			long storageTypeId, int noOfStorageUnits) {
		Warehouse warehouse = warehouseRepo.findById(warehouseId).orElseThrow(() ->
		new WareHouseNotFoundByIdException("Warehouse Id : " + warehouseId + ", is not exist"));

		StorageType storageType = storageTypeRepo.findById(storageTypeId).orElseThrow(()->
		new StorageTypeNotExistException("StorageTypeId : "+storageTypeId+", StorageType is not exist"));

		storageType.setUnitsAvailable(storageType.getUnitsAvailable()+noOfStorageUnits);
		storageType = storageTypeRepo.save(storageType);


		double totalCapacity = storageType.getCapacityInKg() * noOfStorageUnits + warehouse.getTotalCapacity();
		warehouse.setTotalCapacity(totalCapacity);
		warehouseRepo.save(warehouse);

		List<Storage> storages = new ArrayList<Storage>();
		while (noOfStorageUnits > 0) {
			Storage storage = storageMapper.mapStorageRequestToStorage(storageRequest, new Storage());
			storage.setWarehouse(warehouse);
			storage.setStorageType(storageType);
			storage.setMaxAdditionalWeight(storageType.getCapacityInKg());
			storage.setAvailableArea(storageType.getHeightInMeter()*storageType.getBreadthInMeter()*storageType.getBreadthInMeter());

			storages.add(storage);
			noOfStorageUnits--;
		}

		storages = storageRepo.saveAll(storages);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Storage Created")
				.setData(storages.size() + " storages are created"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,
			long storageId) {
		return  storageRepo.findById(storageId).map(storage -> {
	            Storage storage1 = storageMapper.mapStorageRequestToStorage(storageRequest, storage);

	            storage = storageRepo.save(storage1);
	            return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
	                    .setStatus(HttpStatus.OK.value())
	                    .setMessage("Storage Updated")
	                    .setData(storageMapper.mapStorageResponseToStorage(storage)));
	        }).orElseThrow(() -> new StorageNotExistException("StorageId : " + storageId + ", is not exist"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> getStorage(long storageId) {
		return storageRepo.findById(storageId).map(storage->{
           return  ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<StorageResponse>()
                    .setStatus(HttpStatus.FOUND.value())
                    .setMessage("Storage Founded")
                    .setData(storageMapper.mapStorageResponseToStorage(storage)));
        }).orElseThrow(() -> new StorageNotExistException("StorageId : " + storageId + ", is not exist"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> getStorages() {
		 List<StorageResponse> listStorages = storageRepo
	                .findAll()
	                .stream()
	                .map(storageMapper::mapStorageResponseToStorage)
	                .toList();
	        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<StorageResponse>>()
	                .setStatus(HttpStatus.FOUND.value())
	                .setMessage("Storages Founded")
	                .setData(listStorages));
	}


}