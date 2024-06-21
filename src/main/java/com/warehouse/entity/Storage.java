package com.warehouse.entity;

import java.util.List;

import com.warehouse.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Storage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long storageId;
	private String blockname;
	private String section;
	//	private double lengthInMeter;
	//	private double breadthInMeter;
	//	private double heightInMeter;
	//	private double capacityInKg;
	private long sellerId;
	@Enumerated
	private List<MaterialTypes> materialTypes;
	private double maxAdditionalWeight;
	private double availableArea;

	@ManyToOne
	private Warehouse warehouse;

	@ManyToMany(mappedBy = "storages")
	private List<Inventory> inventories;

	@ManyToOne
	private StorageType storageType;
}
