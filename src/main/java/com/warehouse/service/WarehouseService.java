package com.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.requestdto.WarehouseRequest;
import com.warehouse.responsedto.WarehouseResponse;
import com.warehouse.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface WarehouseService {

	ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(@Valid WarehouseRequest warehouseRequest);

	ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
			long warehouseId);

	ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(long warehouseId);

	ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehouses();



	

}
