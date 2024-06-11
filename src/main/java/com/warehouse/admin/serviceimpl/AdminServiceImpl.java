package com.warehouse.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.warehouse.admin.entity.Admin;
import com.warehouse.admin.entity.Warehouse;
import com.warehouse.admin.enums.AdminType;
import com.warehouse.admin.exception.AdminNotFoundException;
import com.warehouse.admin.exception.IllegalOperationException;
import com.warehouse.admin.exception.WareHouseNotFoundByIdException;
import com.warehouse.admin.mapper.AdminMapper;
import com.warehouse.admin.repository.AdminRepo;
import com.warehouse.admin.repository.WarehouseRepo;
import com.warehouse.admin.requestdto.AdminRequest;
import com.warehouse.admin.responsedto.AdminResponse;
import com.warehouse.admin.responsedto.WarehouseResponse;
import com.warehouse.admin.service.AdminService;
import com.warehouse.admin.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private WarehouseRepo warehouseRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
		if(adminRepo.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new IllegalOperationException("super admin already exist!!!!");
		Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin = adminRepo.save(admin);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Super Admin Created!!!!")
				.setData(adminMapper.mapAdminResponseToAdmin(admin)));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmins(AdminRequest adminRequest,
		 long wareHouseId) {
		
		return  warehouseRepo.findById(wareHouseId).map(existwarehouse->{
			Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			admin = adminRepo.save(admin);
//			warehouse.setWareHouseId(warehouse.getWareHouseId());
			existwarehouse.setAdmin(admin);
			warehouseRepo.save(existwarehouse);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.CREATED.value())
					.setMessage("Admin Created!!!!")
					.setData(adminMapper.mapAdminResponseToAdmin(admin)));
		}).orElseThrow(()->new WareHouseNotFoundByIdException("warehouseId:"+wareHouseId+"not found"));
				
	   
}
	

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {
	String email =	SecurityContextHolder.getContext().getAuthentication().getName();
		return  adminRepo.findByEmail(email).map(existadmin->{
			existadmin = adminMapper.mapAdminRequestToAdmin(adminRequest, existadmin);
			
			existadmin = adminRepo.save(existadmin);
						
			return ResponseEntity.ok(new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.OK.value()).setMessage("Admin is updated")
					.setData(adminMapper.mapAdminResponseToAdmin(existadmin)));
		}).orElseThrow(() -> new AdminNotFoundException("Inavalid AdminId"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminbySuperAdmin(AdminRequest adminRequest,
			long adminId) {
		return adminRepo.findById(adminId).map(admin->{
			admin = adminMapper.mapAdminRequestToAdmin(adminRequest, admin);
			//admin.setAdminId(adminId);
			admin=adminRepo.save(admin);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("admin is updated by super admin")
					.setData(adminMapper.mapAdminResponseToAdmin(admin)));
		}).orElseThrow(() -> new AdminNotFoundException("adminId is not present in database"));
		
	}

}