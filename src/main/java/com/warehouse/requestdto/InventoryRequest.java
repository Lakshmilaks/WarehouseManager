package com.warehouse.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.warehouse.entity.Storage;
import com.warehouse.enums.MaterialTypes;

import lombok.Getter;

@Getter
public class InventoryRequest {

	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double WeightInKg;
//	private int quantity;
	private List<MaterialTypes> materialTypes;
	private LocalDate restockedAt;
	private long sellerId;
}
