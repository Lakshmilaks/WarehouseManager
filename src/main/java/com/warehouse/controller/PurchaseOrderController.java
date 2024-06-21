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
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.entity.PurchaseOrders;
import com.warehouse.requestdto.PurchaseOrderRequest;
import com.warehouse.responsedto.InventoryResponse;
import com.warehouse.responsedto.PurchaseOrderResponse;
import com.warehouse.service.PurchaseOrderService;
import com.warehouse.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseService;
	
	
	 @PostMapping("/inventories/{productId}/purchaseOrders")
	    public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest,@PathVariable long productId) {
	        return purchaseService.createPurchaseOrder(purchaseOrderRequest,productId);
	       
	    }
	 
	 @PutMapping("/purchaseOrders/{orderId}")
	    public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> updatePurchaseOrder(
	             @RequestBody PurchaseOrderRequest purchaseOrderRequest,
	             @PathVariable long orderId) {
	        return purchaseService.updatePurchaseOrder(purchaseOrderRequest, orderId);
	    }
	 
	 @GetMapping("/purchaseOrders/{orderId}")
	    public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> findOrder(
	             @PathVariable long orderId) {
	        return purchaseService.findOrder(orderId);
	    }
	 @GetMapping("/purchaseOrder")
	 public ResponseEntity<ResponseStructure<List<PurchaseOrderResponse>>> findOrders(){
		 return purchaseService.findOrders();
	 }
}