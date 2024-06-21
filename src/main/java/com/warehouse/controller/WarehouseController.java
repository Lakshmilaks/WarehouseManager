package com.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.requestdto.WarehouseRequest;
import com.warehouse.responsedto.AddressResponse;
import com.warehouse.responsedto.WarehouseResponse;
import com.warehouse.service.WarehouseService;
import com.warehouse.utility.ResponseStructure;

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
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouse/{warehouseId}")
	public ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(@RequestBody WarehouseRequest warehouseRequest,@PathVariable long warehouseId){
		return warehouseService.updateWarehouse(warehouseRequest,warehouseId);
				
	}
	
		@GetMapping("/warehouse/{warehouseId}")
	public ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(@PathVariable long warehouseId)
	{
		return warehouseService.findWarehouse(warehouseId);
	}
	
	@GetMapping("/warehouses")
	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehouses(){
		return warehouseService.findWarehouses();
	}
	
	
}
