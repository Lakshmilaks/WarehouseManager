package com.warehouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientAlreadyExistException extends RuntimeException {

	private String message;
}
