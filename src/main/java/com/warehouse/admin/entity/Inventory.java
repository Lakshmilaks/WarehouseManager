package com.warehouse.admin.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.warehouse.admin.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double WeightInKg;
//	private int quantity;
	private List<MaterialTypes> materialTypes;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate restockedAt;
	private long sellerId;

	@ManyToMany
	private List<Storage> storages;

	@ManyToOne
	private Client client;

	@ManyToMany(mappedBy = "inventories")
	private List<PurchaseOrders> purchaseOrders;

	@OneToMany(mappedBy = "inventory")
	private List<Batch> batchs;

	
}
