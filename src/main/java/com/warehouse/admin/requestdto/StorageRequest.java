package com.warehouse.admin.requestdto;

import java.util.List;

import com.warehouse.admin.enums.MaterialTypes;

import lombok.Getter;
@Getter
public class StorageRequest {
	private String blockname;
	private String section;
	private Double lengthInMeter;
	private Double breadthInMeter;
	private Double heightInMeter;
	
	private List<MaterialTypes> materialTypes;
	private Double capacityInKg;
}
