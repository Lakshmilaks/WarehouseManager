package com.warehouse.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.AdminRequest;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> createAdmins(AdminRequest adminRequest,long wareHouseId);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminbySuperAdmin(AdminRequest adminRequest, long adminId);

	ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(long adminId);

	ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins();

	
}
