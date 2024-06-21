package com.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storageTypeId;
	private double lengthInMeter;
	private double breadthInMeter;
	private double heightInMeter;
	private double capacityInKg;
	private double unitsAvailable;
}
