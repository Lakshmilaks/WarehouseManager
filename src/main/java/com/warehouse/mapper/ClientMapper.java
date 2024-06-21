package com.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.entity.Client;
import com.warehouse.requestdto.ClientRequest;
import com.warehouse.responsedto.ClientResponse;

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
