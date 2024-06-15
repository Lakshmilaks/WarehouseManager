package com.warehouse.admin.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.admin.entity.Client;
import com.warehouse.admin.requestdto.ClientRequest;
import com.warehouse.admin.responsedto.ClientResponse;

@Component
public class ClientMapper {

	public Client mapClientToClientRequest(ClientRequest clientRequest,Client client) {
		client.setBusinessName(clientRequest.getBusinessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactno(clientRequest.getContactno());
		return client;
	}
	
	public ClientResponse mapClientToClientResponse(Client client) {
				return ClientResponse.builder().apikey(client.getApikey()).build();
	}
}
