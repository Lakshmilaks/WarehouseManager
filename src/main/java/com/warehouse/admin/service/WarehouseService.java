package com.warehouse.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.WarehouseRequest;
import com.warehouse.admin.responsedto.WarehouseResponse;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface WarehouseService {

	ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(@Valid WarehouseRequest warehouseRequest);

	ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
			long warehouseId);

	ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(long warehouseId);

	ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehouses();



	

}
