package com.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.entity.Batch;
import com.warehouse.responsedto.BatchResponse;

@Component
public class BatchMapper {

	public BatchResponse mapBatchToBatchResponse(Batch batch) {
		return BatchResponse.builder().batchId(batch.getBatchId())
				.quantity(batch.getQuantity()).build();
	}
}
