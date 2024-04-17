package com.kitchen.Tiffin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kitchen.Tiffin.model.User;
import com.kitchen.Tiffin.repository.UserRepository;
import com.kitchen.Tiffin.web.dto.UserRegistrationDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository userrepo;

    

    

    @Override
    public User save(UserRegistrationDTO registrationDto) {
        User user = new User(registrationDto.getFirstname(), registrationDto.getLastname(),
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()));

        return userrepo.save(user);
    }
}

	
	


