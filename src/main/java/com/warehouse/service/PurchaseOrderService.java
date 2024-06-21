package com.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.entity.PurchaseOrders;
import com.warehouse.requestdto.PurchaseOrderRequest;
import com.warehouse.responsedto.PurchaseOrderResponse;
import com.warehouse.utility.ResponseStructure;

public interface PurchaseOrderService {


	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest,
			long productId);

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> findOrder(long orderId);

	ResponseEntity<ResponseStructure<List<PurchaseOrderResponse>>> findOrders();

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> updatePurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, long orderId);

}
