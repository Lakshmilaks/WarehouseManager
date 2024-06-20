package com.warehouse.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.InventoryRequest;
import com.warehouse.admin.responsedto.InventoryResponse;
import com.warehouse.admin.utility.ResponseStructure;

public interface InventoryService {


	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,
			long storageId, long clientId, int quantity);

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			long productId);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(long productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories();

}
