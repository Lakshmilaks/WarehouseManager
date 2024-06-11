package com.warehouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.admin.requestdto.WarehouseRequest;
import com.warehouse.admin.responsedto.WarehouseResponse;
import com.warehouse.admin.service.WarehouseService;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@EnableMethodSecurity
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("/warehouse")
	public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(@RequestBody @Valid WarehouseRequest warehouseRequest){
		return warehouseService.createWarehouse(warehouseRequest);
	}
}
