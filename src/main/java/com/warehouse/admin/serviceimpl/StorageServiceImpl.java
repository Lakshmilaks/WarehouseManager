package com.warehouse.admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Storage;
import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.enums.AdminType;
import com.warehouse.admin.exception.StorageNotExistException;
import com.warehouse.admin.exception.WareHouseNotFoundByIdException;
import com.warehouse.admin.mapper.StorageMapper;
import com.warehouse.admin.repository.StorageRepo;
import com.warehouse.admin.repository.WarehouseRepo;
import com.warehouse.admin.requestdto.StorageRequest;
import com.warehouse.admin.responsedto.AddressResponse;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.responsedto.StorageResponse;
import com.warehouse.admin.service.StorageService;
import com.warehouse.admin.utility.ResponseStructure;
@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	private StorageRepo storageRepo;

	@Autowired
	private StorageMapper storageMapper;

	@Autowired
	private WarehouseRepo warehouseRepo;


	@Override
	public ResponseEntity<ResponseStructure<String>> createStorage(long warehouseId,
			StorageRequest storageRequest, int noOfStorageUnits) {

		Warehouse warehouse = warehouseRepo.findById(warehouseId)
				.orElseThrow(()->new WareHouseNotFoundByIdException("WarehouseId not found"));

		double totalCapacity = storageRequest.getCapacityInKg() * noOfStorageUnits + warehouse.getTotalCapacity();
		List<Storage> storages = new ArrayList<Storage>(); 
		while(noOfStorageUnits>0) {
			Storage storage = storageMapper.mapStorageRequestToStorage(storageRequest, new Storage()); 
			storage.setWarehouse(warehouse);
			storages.add(storage);
			noOfStorageUnits--;
		}
		storages = storageRepo.saveAll(storages);
		

		warehouse.setTotalCapacity(totalCapacity);
		warehouse.setStorages(storages);
		warehouseRepo.save(warehouse);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Storages Created!!!!")
				.setData(storages.size()+" storage are created"));

	}

//----------------------------------------------------------------------------------------------------------------------
	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,
			long storageId) {
		return storageRepo.findById(storageId).map(storage -> {
            Storage storage1 = storageMapper.mapStorageRequestToStorage(storageRequest, new Storage());
            storage1.setStorageId(storageId);

            Warehouse warehouse = storage.getWarehouse();
            storage1.setWarehouse(warehouse);
            Double totalCapacity = storageRequest.getCapacityInKg() + warehouse.getTotalCapacity() - storage.getCapacityInKg();
            warehouse.setTotalCapacity(totalCapacity);

            warehouseRepo.save(warehouse);
            storage = storageRepo.save(storage1);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Storage Updated")
                    .setData(storageMapper.mapStorageResponseToStorage(storage)));
        }).orElseThrow(() -> new StorageNotExistException("StorageId is not exist"));
	
	}
	///---------------------------------------------------------------------------------------------

	@Override
	public StorageResponse findFirstByLengthAndBreadthAndCapacity(double lengthInMeter, double breadthInMeter,
			double heightInMeter, double capacityInKg) {
//		   storageRepo.findFirstByLengthAndBreadthAndCapacity(lengthInMeter, breadthInMeter, heightInMeter, capacityInKg)
//	                .map(storage -> storageMapper.mapStorageToStorageResponse(storage))
//	                .orElseThrow(() -> new StorageNotExistException("No storage found with the given dimensions and capacity"));
//	}
//	
	return null;
   
}
}