package com.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.requestdto.StorageTypeRequest;
import com.warehouse.responsedto.StorageTypeResponse;
import com.warehouse.utility.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(long storageTypeId);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes();

}
