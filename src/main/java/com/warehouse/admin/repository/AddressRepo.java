package com.warehouse.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.admin.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

	List<Address> findAllByCity(String city);

	
}
