package com.warehouse.admin.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Batch;
import com.warehouse.admin.entity.Inventory;
import com.warehouse.admin.repository.InventoryRepo;
import com.warehouse.admin.requestdto.InventoryRequest;
import com.warehouse.admin.responsedto.BatchResponse;
import com.warehouse.admin.responsedto.InventoryResponse;

@Component
public class InventoryMapper {

	@Autowired
	private BatchMapper batchMapper;
	
	public Inventory mapInventoryToInventoryRequest(InventoryRequest inventoryRequest, Inventory inventory) {
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setWeightInKg(inventoryRequest.getLengthInMeters());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setRestockedAt(inventoryRequest.getRestockedAt());
		
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;
	}

	public InventoryResponse mapInventoryToInventoryResponse(Inventory inventory, Batch batch) {
		
		BatchResponse batchResponse = batchMapper.mapBatchToBatchResponse(batch);
		
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.lengthInMeters(inventory.getLengthInMeters())
				.breadthInMeters(inventory.getBreadthInMeters())
				.heightInMeters(inventory.getHeightInMeters())
				.WeightInKg(inventory.getWeightInKg())
				.quantity(batch.getQuantity())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.batchs(List.of(batchResponse))
				.build();
	}
	
	 public InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
	        return InventoryResponse.builder()
	                .productId(inventory.getProductId())
	                .productTitle(inventory.getProductTitle())
	                .lengthInMeters(inventory.getLengthInMeters())
	                .breadthInMeters(inventory.getBreadthInMeters())
	                .heightInMeters(inventory.getHeightInMeters())
	                .WeightInKg(inventory.getWeightInKg())
	                .materialTypes(inventory.getMaterialTypes())
	                .restockedAt(inventory.getRestockedAt())
	                .sellerId(inventory.getSellerId())
	                .build();
	    }
}
