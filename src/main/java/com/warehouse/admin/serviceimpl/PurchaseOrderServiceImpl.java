package com.warehouse.admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Batch;
import com.warehouse.admin.entity.Client;
import com.warehouse.admin.entity.PurchaseOrders;
import com.warehouse.admin.exception.InventoryNotExistException;
import com.warehouse.admin.exception.PurchaseOrdersNotExistException;
import com.warehouse.admin.mapper.PurchaseOrderMapper;
import com.warehouse.admin.repository.BatchRepo;
import com.warehouse.admin.repository.InventoryRepo;
import com.warehouse.admin.repository.PurchaseOrderRepo;
import com.warehouse.admin.requestdto.ClientRequest;
import com.warehouse.admin.requestdto.PurchaseOrderRequest;
import com.warehouse.admin.responsedto.ClientResponse;
import com.warehouse.admin.responsedto.PurchaseOrderResponse;
import com.warehouse.admin.service.PurchaseOrderService;
import com.warehouse.admin.utility.ResponseStructure;
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepo orderRepo;

	@Autowired
	private PurchaseOrderMapper mapper;

	@Autowired
	private InventoryRepo inventoryRepo;

	@Autowired
	private BatchRepo batchRepo;

	@Override
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest,
			long productId) {
		
		return inventoryRepo.findById(productId).map(inventory->{
			PurchaseOrders purchaseOrders = mapper.mapPurchaseOrderToRequest(purchaseOrderRequest, new PurchaseOrders());
			purchaseOrders.setInvoiceLink(UUID.randomUUID().toString());
			Batch batch = new Batch();
			batch.setBatchId(productId);
			batch.setQuantity(batch.getQuantity()-purchaseOrders.getOrderQuantity());
			inventory = inventoryRepo.save(inventory);
			batch = batchRepo.save(batch);

			purchaseOrders.setInventories(List.of(inventory));
			purchaseOrders = orderRepo.save(purchaseOrders);

			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<PurchaseOrderResponse>()
					.setStatus(HttpStatus.CREATED.value())
					.setMessage("purchase order created")
					.setData(mapper.mapPurchaseOrderToResponse(purchaseOrders)));
		}).orElseThrow(()->new InventoryNotExistException("Inventory Id is not exist!!!!"));
	}


	@Override
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> findOrder(long orderId) {
		return orderRepo.findById(orderId).map(orders->{
			return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<PurchaseOrderResponse>()
					.setStatus(HttpStatus.FOUND.value())
					.setMessage("order fetched Successfully!!!!")
					.setData(mapper.mapPurchaseOrderToResponse(orders)));
		}).orElseThrow(()->new PurchaseOrdersNotExistException("product Id not found"));
	}


	@Override
	public ResponseEntity<ResponseStructure<List<PurchaseOrderResponse>>> findOrders() {
		List<PurchaseOrderResponse> orderResponses =orderRepo
				.findAll()
				.stream()
				.map(order->mapper.mapPurchaseOrderToResponse(order))
				.toList();
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<PurchaseOrderResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("All Purchase Orders fetched!!!!!")
				.setData(orderResponses));
	}







}
