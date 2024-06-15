package com.warehouse.admin.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.entity.Client;
import com.warehouse.admin.mapper.ClientMapper;
import com.warehouse.admin.repository.ClientRepo;
import com.warehouse.admin.requestdto.ClientRequest;
import com.warehouse.admin.responsedto.ClientResponse;
import com.warehouse.admin.service.ClientService;
import com.warehouse.admin.utility.ResponseStructure;

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
