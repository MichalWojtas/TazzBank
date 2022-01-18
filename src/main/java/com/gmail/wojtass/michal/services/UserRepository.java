package com.gmail.wojtass.michal.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gmail.wojtass.michal.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByUsername(String username);

	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	@Query("from User where accountNumber=?1")
	User findByAccountNumber(String accountNumber);
	
	@Query("Select max(id) from User")
	long findMaxId();
	
	@Modifying
	@Query("update User s set s.accountValue=?1 where s.id=?2")
	void updateAccountValue(double accountValue,long id);
	
	User findById(long id);

}
