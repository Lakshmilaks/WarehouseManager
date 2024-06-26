package com.warehouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.requestdto.AdminRequest;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.service.AdminService;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

	@Autowired
	private AdminService adminService;


	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody @Valid AdminRequest adminRequest){
		return adminService.createSuperAdmin(adminRequest);
	}

	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("warehouse/{warehouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmins(@PathVariable long warehouseId,@RequestBody AdminRequest adminRequest){
		return adminService.createAdmins(adminRequest,warehouseId);
	}

}
