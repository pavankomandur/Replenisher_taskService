package com.personal.replenish.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.replenish.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String username);
	boolean existsByName(String username);
}
