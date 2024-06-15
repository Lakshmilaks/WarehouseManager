package com.warehouse.admin.utility;

import org.springframework.stereotype.Component;

import com.warehouse.admin.responsedto.WarehouseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ResponseStructure<T> {

	private int status;
	private String message;
	private T data;
	
	public ResponseStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}

	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

}

	
