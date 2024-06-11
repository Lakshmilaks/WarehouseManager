package com.warehouse.admin.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.requestdto.WarehouseRequest;
import com.warehouse.admin.responsedto.WarehouseResponse;

@Component
public class WarehouseMapper {

	public Warehouse mapWarehouseRequestToWarehouse(WarehouseRequest warehouseRequest, Warehouse warehouse) {
	
		warehouse.setWarehousename(warehouseRequest.getWarehousename());
		return	warehouse;
		
	}
	
	public WarehouseResponse mapWarehouseResponseToWarehouse(Warehouse warehouse) {
		return WarehouseResponse.builder().wareHouseId(warehouse.getWareHouseId())
				.warehousename(warehouse.getWarehousename())
				.totalCapacity(warehouse.getTotalCapacity())
				.build();
	}
}
