package com.warehouse.serviceimpl;

import java.util.Collection;
import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.warehouse.entity.Admin;
import com.warehouse.entity.Warehouse;
import com.warehouse.enums.AdminType;
import com.warehouse.enums.Privilege;
import com.warehouse.exception.WareHouseNotFoundByIdException;
import com.warehouse.mapper.WarehouseMapper;
import com.warehouse.repository.WarehouseRepo;
import com.warehouse.requestdto.WarehouseRequest;
import com.warehouse.responsedto.AdminResponse;
import com.warehouse.responsedto.WarehouseResponse;
import com.warehouse.service.WarehouseService;
import com.warehouse.utility.ResponseStructure;

import jakarta.validation.Valid;
@Service
public class WarehouseServiceImpl implements WarehouseService{

	@Autowired
	private WarehouseRepo warehouseRepo;

	@Autowired
	private WarehouseMapper warehouseMapper;
	
	@Autowired
	private ResponseStructure<WarehouseResponse> responseStructure;


	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest) {
		Warehouse warehouse = warehouseMapper.mapWarehouseRequestToWarehouse(warehouseRequest,new Warehouse());

		warehouse= warehouseRepo.save(warehouse);
		return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WarehouseResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Warehouse Created!!!!")
				.setData(warehouseMapper.mapWarehouseResponseToWarehouse(warehouse)));
	}


	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
			long warehouseId) {
		return warehouseRepo.findById(warehouseId).map(existwarehouse -> {
			existwarehouse = warehouseMapper.mapWarehouseRequestToWarehouse(warehouseRequest, existwarehouse);
			existwarehouse.setWareHouseId(warehouseId);
			existwarehouse = warehouseRepo.save(existwarehouse);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<WarehouseResponse>()
					.setStatus(HttpStatus.OK.value()).setMessage("warehouse is updated")
					.setData(warehouseMapper.mapWarehouseResponseToWarehouse(existwarehouse)));
		}).orElseThrow(() -> new WareHouseNotFoundByIdException("warehouseId is not present in database"));

	}
	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(long warehouseId) {
		return warehouseRepo.findById(warehouseId).map(warehouse->{

			WarehouseResponse response = warehouseMapper.mapWarehouseResponseToWarehouse(warehouse);
		return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Warehouse Updated!!!!.....")
					.setData(response));

		}).orElseThrow(()-> new WareHouseNotFoundByIdException("Inavaid id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehouses() {
		List<WarehouseResponse> warehouseResponses = warehouseRepo.findAll().stream()
				.map(warehouse->warehouseMapper.mapWarehouseResponseToWarehouse(warehouse)).toList();
		return ResponseEntity.ok(new ResponseStructure<List<WarehouseResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Warehouses fetched Successfully")
				.setData(warehouseResponses));
	}


	

	}