package com.warehouse.admin.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Batch;
import com.warehouse.admin.responsedto.BatchResponse;

@Component
public class BatchMapper {

	public BatchResponse mapBatchToBatchResponse(Batch batch) {
		return BatchResponse.builder().batchId(batch.getBatchId())
				.quantity(batch.getQuantity()).build();
	}
}
