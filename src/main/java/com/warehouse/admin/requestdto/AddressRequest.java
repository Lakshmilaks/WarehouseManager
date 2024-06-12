package com.warehouse.admin.requestdto;

import lombok.Getter;

@Getter
public class AddressRequest {
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String longitude;
	private String latitude;
}
