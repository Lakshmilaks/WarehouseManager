package com.warehouse.admin.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.warehouse.admin.entity.Storage;
import com.warehouse.admin.enums.MaterialTypes;

import lombok.Getter;

@Getter
public class InventoryRequest {

	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double WeightInKg;
	private int quantity;
	private List<MaterialTypes> materialTypes;
	private long sellerId;
}
