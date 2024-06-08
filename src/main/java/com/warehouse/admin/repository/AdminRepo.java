package com.warehouse.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Admin;
import com.warehouse.admin.enums.AdminType;

public interface AdminRepo extends JpaRepository<Admin, Long> {

	boolean existsByAdminType(AdminType adminType);

	Optional<Admin> findByEmail(String email);

	
}
