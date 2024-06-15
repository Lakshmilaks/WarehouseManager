package com.warehouse.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StorageNotExistException extends RuntimeException{

	private String message;
}
