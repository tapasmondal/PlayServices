package com.play.app.facade;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.play.app.dto.UserDTO;
import com.play.app.entity.User;

@Component
public class SecurityUtils {

	private static AuthenticationFacade authenticationFacadeStatic;

	private static ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@PostConstruct
	public void init() {
		SecurityUtils.authenticationFacadeStatic = authenticationFacade;
	}

	public static UserDTO getLoggedInUserDTO() {
		UserDTO user = authenticationFacadeStatic.getLoggedInUser();
		if(user != null) {
			user.setPassword("");
		}
		return user;
	}

	public static User getLoggedInUser() {
		if (authenticationFacadeStatic.getLoggedInUser() != null) {
			return modelMapper.map(authenticationFacadeStatic.getLoggedInUser(), User.class);
		}
		return null;
	}

	public static User getDefaultAdminUser() {
		User user = new User();
		user.setUserId(1);
		return user;
	}
	
}