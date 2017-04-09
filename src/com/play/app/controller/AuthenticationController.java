package com.play.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.play.app.dto.UserDTO;
import com.play.app.facade.SecurityUtils;
import com.play.app.model.UserInfoModel;
import com.play.app.service.PlayInfoService;
import com.play.app.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	PlayInfoService playInfoService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> doLogin(@RequestBody UserInfoModel user) throws IOException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDTO userDetails = SecurityUtils.getLoggedInUserDTO();

		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) authentication;
		HashMap<String, Object> authExtraInfo = new HashMap<String, Object>();
		authExtraInfo.put("loggedInUserId", userDetails.getUserId());
		auth.setDetails(authExtraInfo);

		final String token = "Success";

		HttpHeaders headers = new HttpHeaders();
		headers.add("auth-token", token);
		//userDetails.setPassword("");
		//userDetails.setAuthenticationStatus(token);
		
		UserDTO userDTO=userService.findByUserName(userDetails.getUserName());
		userDTO.setPassword("");
		userDTO.setAuthenticationStatus(token);
		
		System.out.println("Logged in User inforamtion AuthenticationController.doLogin():"+userDTO.toString());
		
		return ResponseEntity.ok().headers(headers).body(userDTO);
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ResponseEntity<?> doLogout(HttpServletRequest request) {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok().body("Logout Success");
	}

	
	

}