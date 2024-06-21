package com.warehouse.service;

import org.springframework.http.ResponseEntity;

import com.warehouse.requestdto.ClientRequest;
import com.warehouse.responsedto.ClientResponse;
import com.warehouse.utility.ResponseStructure;

public interface ClientService {

	ResponseEntity<ResponseStructure<ClientResponse>> createClient(ClientRequest clientRequest);


}
