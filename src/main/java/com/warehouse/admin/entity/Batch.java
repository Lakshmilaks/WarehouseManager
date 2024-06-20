package com.warehouse.admin.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long batchId;
	private int quantity;
	
	@ManyToOne
    private Storage storage;

    @ManyToOne
    private Inventory inventory;
    
    @OneToMany(mappedBy = "storage")
    private List<Batch> batch;
}
