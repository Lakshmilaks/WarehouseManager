package com.warehouse.admin.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.requestdto.WarehouseRequest;
import com.warehouse.admin.responsedto.AddressResponse;
import com.warehouse.admin.responsedto.WarehouseResponse;

@Component
public class WarehouseMapper {

	@Autowired
	private AddressMapper addressMapper;
	public Warehouse mapWarehouseRequestToWarehouse(WarehouseRequest warehouseRequest, Warehouse warehouse) {
	
		warehouse.setWarehousename(warehouseRequest.getWarehousename());
		warehouse.setTotalCapacity(0);
		return	warehouse;
		
	}
		
	public WarehouseResponse mapWarehouseResponseToWarehouse(Warehouse warehouse) {
		return WarehouseResponse.builder().wareHouseId(warehouse.getWareHouseId())
				.warehousename(warehouse.getWarehousename())
				.addressResponse(addressMapper.mapAddressResponseToAddress(warehouse.getAddress()))
				.totalCapacity(warehouse.getTotalCapacity())
				.build();
	}
}
