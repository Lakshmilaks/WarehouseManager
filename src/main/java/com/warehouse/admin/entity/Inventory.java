package com.warehouse.admin.entity;

import java.util.List;

import com.warehouse.admin.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Inventory {
	 @Id
	    private long inventoryId;
	    private String productTitle;
	    private double lengthInMeters;
	    private double breadthInMeters;
	    private double heightInMeters;
	    private double WeightInKg;
	    private int quantity;
	    private List<MaterialTypes> materialTypes;
	    private double restockedAt;
	    private long sellerId;

	    @ManyToMany
	    private List<Storage> storages;

}
