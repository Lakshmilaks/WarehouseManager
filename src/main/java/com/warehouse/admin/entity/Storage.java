package com.warehouse.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Storage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long storageId;
}
