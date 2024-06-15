package com.warehouse.admin.service;

import org.springframework.http.ResponseEntity;

import com.warehouse.admin.requestdto.ClientRequest;
import com.warehouse.admin.responsedto.ClientResponse;
import com.warehouse.admin.utility.ResponseStructure;

public interface ClientService {

	ResponseEntity<ResponseStructure<ClientResponse>> createClient(ClientRequest clientRequest);


}
