package com.personal.replenish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "USER_AUTHORITY")
public class User_Authority{
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role")
    @SequenceGenerator(name = "user_role", sequenceName = "user_role", allocationSize = 1)
    private Long id;
	
	
    @Column(name = "USER_ID", length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String user_id;

    @Column(name = "AUTHORITY_ID", length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    private String authority_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(String authority_id) {
		this.authority_id = authority_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
    

}
