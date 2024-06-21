package com.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.requestdto.ClientRequest;
import com.warehouse.responsedto.ClientResponse;
import com.warehouse.service.ClientService;
import com.warehouse.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<ClientResponse>> createClient(@RequestBody ClientRequest clientRequest) {
		return clientService.createClient(clientRequest);
	}
	
}
