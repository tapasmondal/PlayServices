package com.play.app.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.play.app.dto.UserDTO;
import com.play.app.facade.AuthenticationFacade;
import com.play.app.service.UserService;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Autowired
	UserService userService;

	@Override
	public UserDTO getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {

			String userName = authentication.getName();
			UserDTO userDTO = userService.findByUserName(userName);
			return userDTO;
		}
		return null;
	}
}
