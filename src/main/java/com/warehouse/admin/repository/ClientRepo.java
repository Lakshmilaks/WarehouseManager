package com.warehouse.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Integer>{
 
	
}
