package com.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.requestdto.AdminRequest;
import com.warehouse.responsedto.AdminResponse;
import com.warehouse.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> createAdmins(AdminRequest adminRequest,long wareHouseId);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminbySuperAdmin(AdminRequest adminRequest, long adminId);

	ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(long adminId);

	ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins();

	
}
