package com.warehouse.admin.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.PurchaseOrders;
import com.warehouse.admin.requestdto.PurchaseOrderRequest;
import com.warehouse.admin.responsedto.PurchaseOrderResponse;

@Component
public class PurchaseOrderMapper {

	public PurchaseOrders mapPurchaseOrderToRequest(PurchaseOrderRequest orderRequest,PurchaseOrders orders) {
		orders.setOrderQuantity(orderRequest.getOrderQuantity());
		orders.setInvoiceLink(orderRequest.getInvoiceLink());
		orders.setCustomerId(orderRequest.getCustomerId());
		return orders;
	}
	
	public PurchaseOrderResponse mapPurchaseOrderToResponse(PurchaseOrders orders) {
		return PurchaseOrderResponse.builder().orderId(orders.getOrderId())
				.orderQuantity(orders.getOrderQuantity())
				.invoiceLink(orders.getInvoiceLink())
				.customerId(orders.getCustomerId())
				.build();
	}
}
