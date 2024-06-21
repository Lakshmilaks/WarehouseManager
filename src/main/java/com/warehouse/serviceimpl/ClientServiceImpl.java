package com.warehouse.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.entity.Client;
import com.warehouse.mapper.ClientMapper;
import com.warehouse.repository.ClientRepo;
import com.warehouse.requestdto.ClientRequest;
import com.warehouse.responsedto.ClientResponse;
import com.warehouse.service.ClientService;
import com.warehouse.utility.ResponseStructure;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private ClientRepo clieRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<ClientResponse>> createClient(ClientRequest clientRequest) {
		Client client = clientMapper.mapClientToClientRequest(clientRequest, new Client());
		client.setApikey(UUID.randomUUID().toString());
		client = clieRepo.save(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<ClientResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Client created")
				.setData(clientMapper.mapClientToClientResponse(client)));
	}

}
