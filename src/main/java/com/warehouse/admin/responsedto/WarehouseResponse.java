package com.warehouse.admin.responsedto;

import com.warehouse.admin.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WarehouseResponse {
	private Long wareHouseId;
	private String warehousename;
    private double totalCapacity;
    private AddressResponse addressResponse;

}
