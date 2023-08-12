package com.blogApp.demo.Service;

import com.blogApp.demo.Dto.RegistrationDto;
import com.blogApp.demo.Entity.User;

public interface UserService {
	void saveUser(RegistrationDto registrationDto);
	
	User findByEmail(String email);
}
