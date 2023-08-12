package com.blogApp.demo.Security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogApp.demo.Entity.User;
import com.blogApp.demo.Repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user!=null) {
			org.springframework.security.core.userdetails.User authenticateUser = 
					new org.springframework.security.core.userdetails.User(
							
							user.getEmail(),
							user.getPassword(),
							user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
													.collect(Collectors.toList())
							
							);
			return authenticateUser;
		}else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
	}
	
	
	

}
