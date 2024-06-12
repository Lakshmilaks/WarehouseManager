package com.warehouse.admin.service;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.AddressRequest;
import com.warehouse.admin.responsedto.AddressResponse;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> createAddress(long warehouseId, AddressRequest addressRequest);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(long addresssId, AddressRequest addressRequest);


	ResponseEntity<ResponseStructure<AddressResponse>> findAddress(long addressId);

	

}
