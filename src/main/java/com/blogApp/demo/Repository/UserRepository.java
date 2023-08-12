package com.blogApp.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.demo.Entity.User;
//for user authentication
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
