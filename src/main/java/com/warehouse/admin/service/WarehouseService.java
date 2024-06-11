package com.warehouse.admin.service;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.WarehouseRequest;
import com.warehouse.admin.responsedto.WarehouseResponse;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface WarehouseService {

	ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(@Valid WarehouseRequest warehouseRequest);

}
