package com.warehouse.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.admin.requestdto.StorageTypeRequest;
import com.warehouse.admin.responsedto.StorageTypeResponse;
import com.warehouse.admin.service.StorageTypeService;
import com.warehouse.admin.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class StorageTypeController {

	
	@Autowired
	private StorageTypeService storageTypeService;
	
	@PostMapping("/storageTypes")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(@RequestBody StorageTypeRequest
			storageTypeRequest){
		return storageTypeService.createStorageType(storageTypeRequest);
	}
	
	@GetMapping("storageTypes/{storageTypeId}")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(@PathVariable long storageTypeId){
		return storageTypeService.findStorageType(storageTypeId);
	}
	
	@GetMapping("/storeTypess")
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes(){
		return storageTypeService.findStorageTypes();
	}
}
