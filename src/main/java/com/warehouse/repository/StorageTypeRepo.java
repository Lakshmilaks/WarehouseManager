package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.StorageType;

public interface StorageTypeRepo extends JpaRepository<StorageType, Long> {

}
