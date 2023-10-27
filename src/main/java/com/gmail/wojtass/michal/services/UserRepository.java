package com.gmail.wojtass.michal.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gmail.wojtass.michal.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);

	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	@Query("Select max(userId) from User")
	long findMaxUserId();

	User findByUserId(long id);

}
