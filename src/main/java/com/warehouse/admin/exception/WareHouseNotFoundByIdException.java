package com.warehouse.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WareHouseNotFoundByIdException extends RuntimeException{

	private String message;
}
