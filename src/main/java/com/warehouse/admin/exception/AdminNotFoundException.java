package com.warehouse.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminNotFoundException extends RuntimeException {

	private String messgae;
}
