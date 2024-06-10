package com.warehouse.admin.service;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.AdminRequest;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.utility.ResponseStructure;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> createAdmins(AdminRequest adminRequest);

}
