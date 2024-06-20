package com.warehouse.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Batch;

public interface BatchRepo extends JpaRepository<Batch, Long> {

	 Optional<Batch> findByQuantity(int quantity);
}
