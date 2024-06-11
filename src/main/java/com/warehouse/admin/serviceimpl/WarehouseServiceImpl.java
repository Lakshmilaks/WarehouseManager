package com.warehouse.admin.serviceimpl;

import java.util.Collection;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Admin;
import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.enums.AdminType;
import com.warehouse.admin.enums.Privilege;
import com.warehouse.admin.mapper.WarehouseMapper;
import com.warehouse.admin.repository.WarehouseRepo;
import com.warehouse.admin.requestdto.WarehouseRequest;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.responsedto.WarehouseResponse;
import com.warehouse.admin.service.WarehouseService;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;
@Service
public class WarehouseServiceImpl implements WarehouseService{

	@Autowired
	private WarehouseRepo warehouseRepo;
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	
	
	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest) {
	Warehouse warehouse = warehouseMapper.mapWarehouseRequestToWarehouse(warehouseRequest,new Warehouse());
	
	warehouse= warehouseRepo.save(warehouse);
			return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WarehouseResponse>()
					.setStatus(HttpStatus.CREATED.value())
					.setMessage("Warehouse Created!!!!")
					.setData(warehouseMapper.mapWarehouseResponseToWarehouse(warehouse)));
	}

	
}
