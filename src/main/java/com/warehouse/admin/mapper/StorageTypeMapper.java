package com.warehouse.admin.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.StorageType;
import com.warehouse.admin.requestdto.StorageTypeRequest;
import com.warehouse.admin.responsedto.StorageTypeResponse;

@Component
public class StorageTypeMapper {

	public StorageType mapStorageTypeToStorageTypeRequest(StorageTypeRequest storageTypeRequest,StorageType storageType) {
		storageType.setLengthInMeter(storageTypeRequest.getLengthInMeter());
		storageType.setBreadthInMeter(storageTypeRequest.getBreadthInMeter());
		storageType.setHeightInMeter(storageTypeRequest.getHeightInMeter());
		storageType.setCapacityInKg(storageTypeRequest.getCapacityInKg());
        return storageType;		
	}
	
	public StorageTypeResponse mapStorageTypeToStorageTypeResponse(StorageType storageType) {
		return StorageTypeResponse.builder().storageTypeId(storageType.getStorageTypeId())
				.lengthInMeter(storageType.getLengthInMeter())
				.breadthInMeter(storageType.getBreadthInMeter())
				.heightInMeter(storageType.getHeightInMeter())
				.capacityInKg(storageType.getCapacityInKg())
				.unitsAvailable(storageType.getUnitsAvailable()).build();
	}
}
