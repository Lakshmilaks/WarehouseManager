package com.warehouse.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.entity.Address;
import com.warehouse.entity.Admin;
import com.warehouse.entity.Warehouse;
import com.warehouse.enums.AdminType;
import com.warehouse.exception.AddressNotFoundByIdException;
import com.warehouse.exception.WareHouseNotFoundByIdException;
import com.warehouse.mapper.AddressMapper;
import com.warehouse.mapper.WarehouseMapper;
import com.warehouse.repository.AddressRepo;
import com.warehouse.repository.AdminRepo;
import com.warehouse.repository.WarehouseRepo;
import com.warehouse.requestdto.AddressRequest;
import com.warehouse.responsedto.AddressResponse;
import com.warehouse.responsedto.AdminResponse;
import com.warehouse.responsedto.ClientResponse;
import com.warehouse.responsedto.WarehouseResponse;
import com.warehouse.service.AddressService;
import com.warehouse.utility.ResponseStructure;
@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	
	@Autowired
	private WarehouseRepo warehouseRepo;
	
	@Autowired
	private ResponseStructure<List<WarehouseResponse>> structure;
	
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

	@Override
	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCityForAdmin(String city) {
		List<WarehouseResponse> warehouses = new ArrayList<>();
		addressRepo.findAllByCity(city).forEach(address->{
			AddressResponse response = addressMapper.mapAddressResponseToAddress(address);
			WarehouseResponse warehouseResponse=warehouseMapper.mapWarehouseResponseToWarehouse(address.getWarehouse());
//			warehouseResponse.setAddressResponse(response);
			warehouses.add(warehouseResponse);
			
		});
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<WarehouseResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("warehouses founded!!!")
				.setData(warehouses));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCityForClient(String city) {
		List<WarehouseResponse> warehouses = new ArrayList<>();
		addressRepo.findAllByCity(city).forEach(address->{
			AddressResponse response = addressMapper.mapAddressResponseToAddress(address);
			WarehouseResponse warehouseResponse=warehouseMapper.mapWarehouseResponseToWarehouse(address.getWarehouse());
//			warehouseResponse.setAddressResponse(response);
			warehouses.add(warehouseResponse);
			
		});
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<WarehouseResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("warehouses founded!!!")
				.setData(warehouses));
	}

	
	

}
