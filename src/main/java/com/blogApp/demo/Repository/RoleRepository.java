package com.blogApp.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.demo.Entity.Role;
//for user authentication
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
