package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Admin;
import com.warehouse.entity.Warehouse;
import com.warehouse.enums.AdminType;

public interface AdminRepo extends JpaRepository<Admin, Long> {

	boolean existsByAdminType(AdminType adminType);

	Optional<Admin> findByEmail(String email);

	List<Admin> findAllByAdminType(AdminType admin);

	
}
