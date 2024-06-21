package com.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.requestdto.AddressRequest;
import com.warehouse.responsedto.AddressResponse;
import com.warehouse.responsedto.ClientResponse;
import com.warehouse.responsedto.WarehouseResponse;
import com.warehouse.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> createAddress(long warehouseId, AddressRequest addressRequest);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(long addresssId, AddressRequest addressRequest);


	ResponseEntity<ResponseStructure<AddressResponse>> findAddress(long addressId);
	

//	ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCity(String city);

	ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCityForAdmin(String city);

	ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCityForClient(String city);

	

}
