package com.warehouse.admin.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Address;
import com.warehouse.admin.requestdto.AddressRequest;
import com.warehouse.admin.responsedto.AddressResponse;
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
