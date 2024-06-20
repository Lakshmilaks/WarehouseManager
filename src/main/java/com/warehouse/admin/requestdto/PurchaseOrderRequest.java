package com.warehouse.admin.requestdto;

import lombok.Getter;

@Getter
public class PurchaseOrderRequest {

	private int orderQuantity;
	private String invoiceLink;
	private int customerId;
}
