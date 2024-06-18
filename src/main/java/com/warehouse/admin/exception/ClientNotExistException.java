package com.warehouse.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientNotExistException extends RuntimeException{

	private String message;
}
