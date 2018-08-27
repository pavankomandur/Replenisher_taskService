package com.personal.replenish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.replenish.entity.User_Authority;

public interface UserRoleRepository extends JpaRepository<User_Authority, Long> {
	

}
