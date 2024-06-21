package com.warehouse.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponse {
	private long addressId;
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String longitude;
	private String latitude;
}
