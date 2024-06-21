package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.PurchaseOrders;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrders, Long> {

}
