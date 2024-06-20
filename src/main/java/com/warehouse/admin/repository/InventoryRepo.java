package com.warehouse.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {

}
