package com.warehouse.admin.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.warehouse.admin.entity.Client;
import com.warehouse.admin.exception.ClientNotExistException;
import com.warehouse.admin.exception.IllegalOperationException;
import com.warehouse.admin.exception.UserNotFoundException;
import com.warehouse.admin.repository.ClientRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientApiKeyFilter  extends OncePerRequestFilter{

	@Autowired
	private ClientRepo clientRepo;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 if (request.getSession(false) != null)
	            throw new IllegalOperationException("Please logout first....!!!");

	        if (!request.getRequestURI().equals("/api/v1/client/register")) {
	            String apiKey = request.getHeader("API-KEY");
	            String username = request.getHeader("USERNAME");

	            if (apiKey != null || username != null) {
	                Client client = clientRepo.findByEmail(username)
	                        .orElseThrow(() -> new ClientNotExistException("Email  does not exist"));
	                if (!apiKey.equals(client.getApikey())) {
	                    throw new BadCredentialsException("Invalid Credential");
	                }
	            } else
	                throw new UserNotFoundException("User not found");
	        }
	        filterChain.doFilter(request, response);
	    }
		
	

}
