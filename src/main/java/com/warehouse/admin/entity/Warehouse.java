package com.warehouse.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Warehouse {
	@Id
	private Long wareHouseId;
	private String name;
	@OneToOne
	private Admin admin;
}