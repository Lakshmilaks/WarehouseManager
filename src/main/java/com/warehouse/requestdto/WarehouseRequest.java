package com.warehouse.requestdto;

import com.warehouse.responsedto.AddressResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class WarehouseRequest {

	@NotNull(message = "StoreHouse name can not be null")
	@NotBlank(message = "StoreHouse name can not be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters.")
	private String warehousename;
	
}
