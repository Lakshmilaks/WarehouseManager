package com.warehouse.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.StorageTypeRequest;
import com.warehouse.admin.responsedto.StorageTypeResponse;
import com.warehouse.admin.utility.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(long storageTypeId);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes();

}
