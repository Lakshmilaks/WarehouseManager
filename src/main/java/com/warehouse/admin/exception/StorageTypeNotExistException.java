package com.warehouse.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StorageTypeNotExistException extends RuntimeException
{

	private String message;
}
