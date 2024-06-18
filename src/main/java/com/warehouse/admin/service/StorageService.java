package com.warehouse.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.StorageRequest;
import com.warehouse.admin.responsedto.StorageResponse;
import com.warehouse.admin.utility.ResponseStructure;

public interface StorageService {

	ResponseEntity<ResponseStructure<String>> createStorage(StorageRequest storageRequest, long warehouseId,
			long storageTypeId, int noOfStorageUnits);

	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest, long storageId);

	ResponseEntity<ResponseStructure<StorageResponse>> getStorage(long storageId);

	ResponseEntity<ResponseStructure<List<StorageResponse>>> getStorages();

	

	


	 
}
