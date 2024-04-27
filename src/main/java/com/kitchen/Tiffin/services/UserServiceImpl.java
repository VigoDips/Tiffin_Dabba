package com.kitchen.Tiffin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kitchen.Tiffin.model.User;
import com.kitchen.Tiffin.repository.UserRepository;
import com.kitchen.Tiffin.web.dto.UserRegistrationDTO;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(UserRegistrationDTO registrationDto) {
        User user = new User(registrationDto.getFirstname(), registrationDto.getLastname(),
                             registrationDto.getEmail(), registrationDto.getRole(),passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }
}
