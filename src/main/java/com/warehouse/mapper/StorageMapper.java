package com.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.entity.Storage;
import com.warehouse.requestdto.StorageRequest;
import com.warehouse.responsedto.StorageResponse;

@Component
public class StorageMapper {

	public Storage mapStorageRequestToStorage(StorageRequest storageRequest,Storage storage){
//		 Double capacityInArea = storageRequest.getLengthInMeter()
//				 *storageRequest.getBreadthInMeter()
//				 *storageRequest.getHeightInMeter();
		
		storage.setBlockname(storageRequest.getBlockname());
		storage.setSection(storageRequest.getSection());
//		storage.setCapacityInKg(storageRequest.getCapacityInKg());
		
//		storage.setLengthInMeter(storageRequest.getLengthInMeter());
//		storage.setBreadthInMeter(storageRequest.getBreadthInMeter());
//		storage.setHeightInMeter(storageRequest.getHeightInMeter());
		
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
//		storage.setMaxAdditionalWeight(storageRequest.getCapacityInKg());
//		storage.setAvailableArea(capacityInArea);
		return storage;
	}
	
	public StorageResponse mapStorageResponseToStorage(Storage storage) {
	    return StorageResponse.builder()
	            .storageId(storage.getStorageId())
	            .blockname(storage.getBlockname())
	            .section(storage.getSection())
//	            .capacityInKg(storage.getCapacityInKg())
//	            
//	            .lengthInMeter(storage.getLengthInMeter())
//	            .breadthInMeter(storage.getBreadthInMeter())
//	            .heightInMeter(storage.getHeightInMeter())
	            
	            .materialTypes(storage.getMaterialTypes())
	            .maxAdditionalWeight(storage.getMaxAdditionalWeight())
	            .availableArea(storage.getAvailableArea())
	            .build();
	}
}
