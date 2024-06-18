package com.warehouse.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.StorageType;

public interface StorageTypeRepo extends JpaRepository<StorageType, Long> {

}
