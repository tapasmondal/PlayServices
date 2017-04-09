package com.play.app.facade;

import com.play.app.dto.UserDTO;

public interface AuthenticationFacade {
	UserDTO getLoggedInUser();
}