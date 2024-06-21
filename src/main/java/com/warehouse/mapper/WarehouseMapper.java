package com.warehouse.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.warehouse.entity.Warehouse;
import com.warehouse.requestdto.WarehouseRequest;
import com.warehouse.responsedto.AddressResponse;
import com.warehouse.responsedto.WarehouseResponse;

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
