package com.warehouse.responsedto;

import java.util.List;

import com.warehouse.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageResponse {

	private long storageId;
	private String blockname;
	private String section;
//	private Double lengthInMeter;
//	private Double breadthInMeter;
//	private Double heightInMeter;
//	private Double capacityInKg;
	private List<MaterialTypes> materialTypes;
	private Double maxAdditionalWeight;
    private Double availableArea;
}
