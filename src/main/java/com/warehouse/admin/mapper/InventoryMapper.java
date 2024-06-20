package com.warehouse.admin.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Inventory;
import com.warehouse.admin.repository.InventoryRepo;
import com.warehouse.admin.requestdto.InventoryRequest;
import com.warehouse.admin.responsedto.InventoryResponse;

@Component
public class InventoryMapper {

		public Inventory mapInventoryToInventoryRequest(InventoryRequest inventoryRequest,Inventory inventory) {
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setWeightInKg(inventoryRequest.getLengthInMeters());
		inventory.setQuantity(inventoryRequest.getQuantity());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;
	}
	
	public InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.lengthInMeters(inventory.getLengthInMeters())
				.breadthInMeters(inventory.getBreadthInMeters())
				.heightInMeters(inventory.getHeightInMeters())
				.WeightInKg(inventory.getWeightInKg())
				.quantity(inventory.getQuantity())
				.materialTypes(inventory.getMaterialTypes())
				.sellerId(inventory.getSellerId()).build();
	}
}
