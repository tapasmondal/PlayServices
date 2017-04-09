package com.play.app.service;

import java.util.List;

import com.play.app.dto.UserDTO;

public interface UserService {

	UserDTO findById(Integer id);

	UserDTO saveUser(UserDTO user);

	void deleteUserById(Integer id);

	List<UserDTO> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(UserDTO user);

	UserDTO findByUserId(Integer userId);

	UserDTO findByUserName(String userName);

	UserDTO resetPassword(UserDTO user);


}
