package com.warehouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressNotFoundByIdException extends RuntimeException{

	private String message ;
}
