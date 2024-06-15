package com.warehouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.admin.requestdto.ClientRequest;
import com.warehouse.admin.responsedto.ClientResponse;
import com.warehouse.admin.service.ClientService;
import com.warehouse.admin.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<String>> createClient(@RequestBody ClientRequest clientRequest) {
		return clientService.createClient(clientRequest);
	}
	
}
