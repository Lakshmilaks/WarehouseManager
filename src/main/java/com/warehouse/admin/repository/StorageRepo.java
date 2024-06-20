package com.warehouse.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Storage;

public interface StorageRepo extends JpaRepository<Storage, Long>{

	

	}
