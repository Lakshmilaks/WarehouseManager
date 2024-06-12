package com.warehouse.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.admin.requestdto.AddressRequest;
import com.warehouse.admin.requestdto.AdminRequest;
import com.warehouse.admin.responsedto.AddressResponse;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.service.AddressService;
import com.warehouse.admin.utility.ResponseStructure;


@RestController
@RequestMapping("/api/v1")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/{warehouseId}/address")
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@PathVariable long warehouseId,@RequestBody AddressRequest addressRequest){
		return addressService.createAddress(warehouseId,addressRequest);
	}
	
	@PutMapping("/address/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@PathVariable long addressId,
			@RequestBody AddressRequest addressRequest){
		return addressService.updateAddress(addressId,addressRequest);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(@PathVariable long addressId){
		return addressService.findAddress(addressId);
	}
	
}
