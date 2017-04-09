package com.play.app.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUserId(Integer userId);
	
	User findByEmail(String email);
	
	User findByUserName(String userName);
	
	
	@Modifying
	@Query(value="update user_account  set user_password = ?1, lastmodifieddate=?2 where id = ?3", nativeQuery=true)
	void updatePassword(String password, Date modifiedDate, Integer userId);
}
