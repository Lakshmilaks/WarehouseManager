package com.warehouse.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.PurchaseOrders;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrders, Long> {

}
