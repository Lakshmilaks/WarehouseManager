package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {

}
