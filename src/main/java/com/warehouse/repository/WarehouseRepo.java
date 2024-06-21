package com.warehouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long>{

	

}
