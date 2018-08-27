package com.personal.replenish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.replenish.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT p FROM User p WHERE LOWER(p.username) = LOWER(:username)")
	User findByUsername(@Param("username") String username);

   // User findByUsername(String username);
}
