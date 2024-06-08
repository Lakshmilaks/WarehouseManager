package com.warehouse.admin.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Admin;
import com.warehouse.admin.requestdto.AdminRequest;
import com.warehouse.admin.responsedto.AdminResponse;

@Component
public class AdminMapper {

	public Admin mapAdminRequestToAdmin(AdminRequest adminRequest,Admin admin) {
		admin.setName(adminRequest.getName());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(adminRequest.getPassword());
		return admin;
	}
	
	public AdminResponse mapAdminResponseToAdmin(Admin admin) {
		return AdminResponse.builder().adminId(admin.getAdminId())
				.name(admin.getName()).email(admin.getEmail()).build();
	}
}
