package com.warehouse.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.warehouse.admin.repository.AdminRepo;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return adminRepo.findByEmail(username).map(UserDetailImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Credencial"));
	}

}
