package com.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.requestdto.InventoryRequest;
import com.warehouse.responsedto.InventoryResponse;
import com.warehouse.service.InventoryService;
import com.warehouse.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/{clientId}/storages/{storageId}/inventories")
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(@RequestBody InventoryRequest inventoryRequest,
			@PathVariable long storageId,@PathVariable long clientId,@RequestParam("quantity") int quantity){
		return inventoryService.createInventory(inventoryRequest,storageId,clientId,quantity);
	}
	
	 @PutMapping("/inventories/{productId}")
	    public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
	             @RequestBody InventoryRequest inventoryRequest,
	             @PathVariable long productId) {
	        return inventoryService.updateInventory(inventoryRequest, productId);
	    }
	 
	 @GetMapping("/inventories/{productId}")
	    public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(
	             @PathVariable long productId) {
	        return inventoryService.findInventory(productId);
	    }
	 
	  @GetMapping("/inventories")
	    public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories(){
	        return inventoryService.findInventories();
	    }
}
