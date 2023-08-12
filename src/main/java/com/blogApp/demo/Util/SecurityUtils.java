package com.blogApp.demo.Util;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;


public class SecurityUtils {
	//here user is not the user entity instead it's coming from another package .
	public static User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof User) {
			return (User)principal;
		}
		return null;
	}
	
}
