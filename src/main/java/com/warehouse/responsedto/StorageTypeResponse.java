package com.warehouse.responsedto;

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
public class StorageTypeResponse {

	private Long storageTypeId;
	private double lengthInMeter;
	private double breadthInMeter;
	private double heightInMeter;
	private double capacityInKg;
	private double unitsAvailable;
}
