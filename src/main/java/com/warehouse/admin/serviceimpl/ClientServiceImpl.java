package com.warehouse.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.warehouse.admin.repository.ClientRepo;
import com.warehouse.admin.requestdto.ClientRequest;
import com.warehouse.admin.service.ClientService;
import com.warehouse.admin.utility.ResponseStructure;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepo clientRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<String>> createClient(ClientRequest clientRequest) {
		return null;
	}

}
