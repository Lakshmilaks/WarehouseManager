package com.warehouse.admin.responsedto;

import java.util.UUID;

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
public class ApikeyResponse {
	private String apikey;
	
	public String generateApiKey() {
		UUID uuid = UUID.randomUUID();
		apikey = uuid.toString();
		return apikey;
	}
}
