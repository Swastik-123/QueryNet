package com.blogApp.demo.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	
	//many to many relationship between role and user table.
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) //v.87
	@JoinTable(
			name="users_roles",
			joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")}
			)
	
	private List<Role> roles = new ArrayList<>();
	
	
	
}
