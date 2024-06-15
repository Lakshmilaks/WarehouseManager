package com.warehouse.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Storage;

public interface StorageRepo extends JpaRepository<Storage, Long>{

}
