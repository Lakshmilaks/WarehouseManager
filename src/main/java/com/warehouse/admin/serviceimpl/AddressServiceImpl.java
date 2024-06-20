package com.warehouse.admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Address;
import com.warehouse.admin.entity.Admin;
import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.enums.AdminType;
import com.warehouse.admin.exception.AddressNotFoundByIdException;
import com.warehouse.admin.exception.WareHouseNotFoundByIdException;
import com.warehouse.admin.mapper.AddressMapper;
import com.warehouse.admin.mapper.WarehouseMapper;
import com.warehouse.admin.repository.AddressRepo;
import com.warehouse.admin.repository.AdminRepo;
import com.warehouse.admin.repository.WarehouseRepo;
import com.warehouse.admin.requestdto.AddressRequest;
import com.warehouse.admin.responsedto.AddressResponse;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.responsedto.ClientResponse;
import com.warehouse.admin.responsedto.WarehouseResponse;
import com.warehouse.admin.service.AddressService;
import com.warehouse.admin.utility.ResponseStructure;
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
