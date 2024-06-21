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
import com.warehouse.admin.entity.Inventory;
import com.warehouse.admin.entity.PurchaseOrders;
import com.warehouse.admin.exception.InventoryNotExistException;
import com.warehouse.admin.exception.PurchaseOrderNotCompletedException;
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
			PurchaseOrders purchaseOrder = mapper.mapPurchaseOrderToRequest(purchaseOrderRequest, new PurchaseOrders());
			purchaseOrder.setInvoiceLink(UUID.randomUUID().toString().concat(".jpg"));
			 int availableQuantity = inventory.getBatchs().getFirst().getQuantity();
			 
	            if(availableQuantity>=purchaseOrderRequest.getOrderQuantity()){
	                int temp = availableQuantity - purchaseOrder.getOrderQuantity();
	                Batch batch = inventory.getBatchs().getFirst();
	                batch.setQuantity(temp);
	                inventory.getBatchs().addFirst(batch);
			
						inventory = inventoryRepo.save(inventory);
						 purchaseOrder.setInventories(List.of(inventory));
			                purchaseOrder = orderRepo.save(purchaseOrder);
			            }else {
			                throw new PurchaseOrderNotCompletedException("Please reduce quantity");
			            }
			         return    ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<PurchaseOrderResponse>()
			                    .setStatus(HttpStatus.CREATED.value())
			                    .setMessage("PurchaseOrder Created")
			                    .setData(mapper.mapPurchaseOrderToResponse(purchaseOrder)));
			        }).orElseThrow(() -> new InventoryNotExistException("ProductId : " + productId + ", is not exist"));
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


	@Override
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> updatePurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, long orderId) {
		 
		        return orderRepo.findById(orderId).map(purchaseOrder -> {
		            int oldOrderQnt = purchaseOrder.getOrderQuantity();
		            int newOrderQnt = purchaseOrderRequest.getOrderQuantity();

//		         TODO note :-> this method is working only if purchase order have only one inventory
		            List<Inventory> listInventories = purchaseOrder.getInventories();
		            if (newOrderQnt > oldOrderQnt) {
		                int updateOrderQnt = newOrderQnt - oldOrderQnt;
		                listInventories.forEach(inventory -> {
//		                    inventory.setQuantity(inventory.getQuantity()-updateOrderQnt);
		                    inventoryRepo.save(inventory);
		                });
		            } else {
		                int updateOrderQnt = oldOrderQnt - newOrderQnt;
		                listInventories.forEach(inventory -> {
//		                    inventory.setQuantity(inventory.getQuantity()+updateOrderQnt);
		                    inventoryRepo.save(inventory);
		                });
		            }
		            purchaseOrder = mapper.mapPurchaseOrderToRequest(purchaseOrderRequest, purchaseOrder);
		          return   ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<PurchaseOrderResponse>()
		                    .setStatus(HttpStatus.OK.value())
		                    .setMessage("PurchaseOrder Updated")
		                    .setData(mapper.mapPurchaseOrderToResponse(purchaseOrder)));
		        }).orElseThrow(() -> new PurchaseOrdersNotExistException("OrderId : " + orderId + ", is not exist"));
	}







}
