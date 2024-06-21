package com.warehouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.entity.Storage;
import com.warehouse.entity.StorageType;
import com.warehouse.exception.StorageTypeNotFoundException;
import com.warehouse.mapper.StorageTypeMapper;
import com.warehouse.repository.StorageRepo;
import com.warehouse.repository.StorageTypeRepo;
import com.warehouse.requestdto.StorageTypeRequest;
import com.warehouse.responsedto.StorageTypeResponse;
import com.warehouse.service.StorageTypeService;
import com.warehouse.utility.ResponseStructure;

@Service
public class StorageTypeServiceImpl implements StorageTypeService{

	@Autowired
	private StorageTypeRepo storageTypeRepo;
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;
	
	
	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
		
		StorageType storageType = storageTypeMapper.mapStorageTypeToStorageTypeRequest(storageTypeRequest, new StorageType());
		storageType = storageTypeRepo.save(storageType);
		
		StorageTypeResponse storageTypeResponse = storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType);
	    ResponseStructure<StorageTypeResponse> responseStructure = new ResponseStructure<StorageTypeResponse>();
	    responseStructure.setData(storageTypeResponse);
	    responseStructure.setMessage("Storage type created successfully");
	    responseStructure.setStatus(HttpStatus.CREATED.value());

	    return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
		
		
	}


	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(long storageTypeId) {
		return storageTypeRepo.findById(storageTypeId).map(storageType->{
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<StorageTypeResponse>()
					.setStatus(HttpStatus.FOUND.value())
					.setMessage("storageType founded")
					.setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
		}).orElseThrow(()->new StorageTypeNotFoundException("storageType not found"));
		
	}


	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes() {
		List<StorageTypeResponse> storageTypeResponses = storageTypeRepo.findAll().stream().map(storeType->{
			return storageTypeMapper.mapStorageTypeToStorageTypeResponse(storeType);
		}).toList();
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<StorageTypeResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("storageType founded")
				.setData(storageTypeResponses));
		
	}

}
