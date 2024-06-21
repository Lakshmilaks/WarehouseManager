package com.warehouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{
 
	 boolean existsByEmail(String email);
	    Optional<Client> findByEmail(String email);
}