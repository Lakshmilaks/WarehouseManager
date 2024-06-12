package com.warehouse.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Address;
import com.warehouse.admin.entity.Admin;
import com.warehouse.admin.enums.AdminType;
import com.warehouse.admin.exception.AddressNotFoundByIdException;
import com.warehouse.admin.exception.WareHouseNotFoundByIdException;
import com.warehouse.admin.mapper.AddressMapper;
import com.warehouse.admin.repository.AddressRepo;
import com.warehouse.admin.repository.AdminRepo;
import com.warehouse.admin.repository.WarehouseRepo;
import com.warehouse.admin.requestdto.AddressRequest;
import com.warehouse.admin.responsedto.AddressResponse;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.service.AddressService;
import com.warehouse.admin.utility.ResponseStructure;
@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private WarehouseRepo warehouseRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(long warehouseId,
			AddressRequest addressRequest) {
		return  warehouseRepo.findById(warehouseId).map(warehouse->{
			Address address = addressMapper.mapAddressRequestToAddress(addressRequest, new Address());
			address.setWarehouse(warehouse);
			address = addressRepo.save(address);
				
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
					.setStatus(HttpStatus.CREATED.value())
					.setMessage("Address Created!!!!")
					.setData(addressMapper.mapAddressResponseToAddress(address)));
		}).orElseThrow(()->new WareHouseNotFoundByIdException("warehouseId not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(long addresssId,
			AddressRequest addressRequest) {
		
		return addressRepo.findById(addresssId).map(existaddress->{
			 existaddress=addressMapper.mapAddressRequestToAddress(addressRequest, existaddress);
			 existaddress=addressRepo.save(existaddress);
			 
			return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Address Created!!!!")
						.setData(addressMapper.mapAddressResponseToAddress(existaddress)));
			}).orElseThrow(()->new AddressNotFoundByIdException("addressId not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(long addressId) {
		
		return addressRepo.findById(addressId).map(address->{
			return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
					.setStatus(HttpStatus.CREATED.value())
					.setMessage("Address Created!!!!")
					.setData(addressMapper.mapAddressResponseToAddress(address)));
		}).orElseThrow(()->new AddressNotFoundByIdException("addressId not found"));

		
	}

	
	

}
