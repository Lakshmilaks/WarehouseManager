package com.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.entity.Address;
import com.warehouse.requestdto.AddressRequest;
import com.warehouse.responsedto.AddressResponse;

@Component
public class AddressMapper {
	
	public Address mapAddressRequestToAddress(AddressRequest addressRequest,Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setLongitude(addressRequest.getLongitude());
		address.setLatitude(addressRequest.getLatitude());
		return address;
	}
	
	public AddressResponse mapAddressResponseToAddress(Address address) {
		return AddressResponse.builder().addressId(address.getAddressId())
				.addressLine(address.getAddressLine()).city(address.getCity())
				.country(address.getCountry()).state(address.getState()).pincode(address.getPincode())
				.longitude(address.getLongitude()).latitude(address.getLatitude()).build();
	}
}
