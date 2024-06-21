package com.warehouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IllegalOperationException extends RuntimeException {

	private String message;
}
