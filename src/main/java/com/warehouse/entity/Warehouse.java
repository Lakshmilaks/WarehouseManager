package com.warehouse.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UUID;

import com.warehouse.responsedto.AddressResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Warehouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wareHouseId;
	private String warehousename;
	private double totalCapacity;
	@OneToOne
	private Admin admin;
	@OneToMany(mappedBy = "warehouse")
	private List<Storage> storages = new ArrayList();
	@OneToOne
	private Address address;
	
	
}
