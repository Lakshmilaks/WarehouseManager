package com.warehouse.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.admin.mapper.StorageMapper;
import com.warehouse.admin.requestdto.StorageRequest;
import com.warehouse.admin.responsedto.StorageResponse;
import com.warehouse.admin.service.StorageService;
import com.warehouse.admin.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class StorageController {

	@Autowired
	private StorageService storageService;


	@PostMapping("/warehouse/{warehouseId}/storage")
	@PreAuthorize("hasAuthority('CREATE_STORAGE')")
	public ResponseEntity<ResponseStructure<String>> createStorage(@PathVariable long warehouseId,
			@RequestBody StorageRequest storageRequest, @RequestParam("no_of_storage_units") int noOfStorageUnits) {
		return storageService.createStorage(warehouseId, storageRequest, noOfStorageUnits);
	}

	@PutMapping("/storage/{storageId}")
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@RequestBody StorageRequest storageRequest,
			@PathVariable long storageId ){
		return storageService.updateStorage(storageRequest,storageId);
	}

//	@PostMapping("/demo/{lengthInMeter}/{breadthInMeter}/{heightInMeter}/{capacityInKg}")
//	public StorageResponse findFirstByLengthAndBreadthAndCapacity(@PathVariable double lengthInMeter,
//			@PathVariable double  breadthInMeter,
//			@PathVariable double heightInMeter,
//			@PathVariable  double capacityInKg) {
//		return storageService.findFirstByLengthAndBreadthAndCapacity(lengthInMeter,breadthInMeter,heightInMeter,capacityInKg);
//	}
}
