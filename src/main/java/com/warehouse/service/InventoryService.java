package com.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.requestdto.InventoryRequest;
import com.warehouse.responsedto.InventoryResponse;
import com.warehouse.utility.ResponseStructure;

public interface InventoryService {


	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,
			long storageId, long clientId, int quantity);

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			long productId);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(long productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories();

}
