package com.warehouse.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.entity.PurchaseOrders;
import com.warehouse.admin.requestdto.PurchaseOrderRequest;
import com.warehouse.admin.responsedto.PurchaseOrderResponse;
import com.warehouse.admin.utility.ResponseStructure;

public interface PurchaseOrderService {


	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest,
			long productId);

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> findOrder(long orderId);

	ResponseEntity<ResponseStructure<List<PurchaseOrderResponse>>> findOrders();

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> updatePurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, long orderId);

}
