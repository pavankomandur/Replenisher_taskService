package com.personal.replenish.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.personal.replenish.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//Optional<User> findByName(String username);
	//boolean existsByName(String username);
	 @Query("SELECT p FROM User p WHERE LOWER(p.user_id) = LOWER(:user_id)")
	User findByUser_id(@Param("user_id") String user_id);
}
