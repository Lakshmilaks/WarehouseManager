package com.warehouse.admin.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.warehouse.admin.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class InventoryResponse {

	private long productId;
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double WeightInKg;
	private int quantity;
	private List<MaterialTypes> materialTypes;
	private LocalDate restockedAt;
	private long sellerId;
	private List<BatchResponse> batchs;
}
