package com.kitchen.Tiffin.services;

import com.kitchen.Tiffin.model.User;
import com.kitchen.Tiffin.web.dto.UserRegistrationDTO;

public interface UserService {
User save(UserRegistrationDTO registrationDto);
}
