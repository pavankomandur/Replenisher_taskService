package com.personal.replenish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//	
	
	
	@Column(name = "USER_ID")
	private String user_id;
	
	
	@Column(name = "PASSWORD")
	private String password;
	
	@NotNull
	@Column(name = "NAME", unique = true)
	private String name;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ROLE")
	private Role role;

	public User() {
		
	}
	
	public User(User user) {
		this.user_id=user.getUser_id();
		this.password =  user.getPassword();
		this.name = user.getName();
		this.role = user.getRole();
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}	
	
	
}