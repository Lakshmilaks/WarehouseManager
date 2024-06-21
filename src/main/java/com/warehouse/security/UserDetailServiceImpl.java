package com.warehouse.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.warehouse.repository.AdminRepo;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepo adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		return adminRepository.findByEmail(username).map(admin -> {
//			return new UserDetailImpl(admin);
//		}).orElseThrow(() -> new UsernameNotFoundException("Invalid Credencial"));
		
		return adminRepository.findByEmail(username).map(UserDetailImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Credencial"));
	}

}