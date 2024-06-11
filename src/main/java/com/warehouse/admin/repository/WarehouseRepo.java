package com.warehouse.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long>{

	

}
