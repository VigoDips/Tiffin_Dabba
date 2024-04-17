package com.kitchen.Tiffin.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kitchen.Tiffin.model.User;
import com.kitchen.Tiffin.repository.UserRepository;

@Service 
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userrepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomerUserDetail(user);
	}

}
