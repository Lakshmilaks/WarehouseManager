package com.warehouse.requestdto;

import java.util.List;

import com.warehouse.enums.MaterialTypes;

import lombok.Getter;
@Getter
public class StorageRequest {
	private String blockname;
	private String section;
//	private double lengthInMeter;
//	private double breadthInMeter;
//	private double heightInMeter;
	
	private List<MaterialTypes> materialTypes;
//	private double capacityInKg;
}
