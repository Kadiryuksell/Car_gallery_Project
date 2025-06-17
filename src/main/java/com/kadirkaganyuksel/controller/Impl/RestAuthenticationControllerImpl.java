package com.kadirkaganyuksel.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kadirkaganyuksel.controller.IRestAuthenticationController;
import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.AuthResponse;
import com.kadirkaganyuksel.dto.DtoUser;
import com.kadirkaganyuksel.dto.RefreshTokenRequest;
import com.kadirkaganyuksel.jwt.AuthRequest;
import com.kadirkaganyuksel.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController{

	@Autowired
	private IAuthenticationService authenticationService;
	
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody  AuthRequest input) {
		return ok(authenticationService.register(input));
	}


	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
		
		return ok(authenticationService.authenticate(request));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> resfreshToken(@Valid @RequestBody RefreshTokenRequest request) {
		
		return ok(authenticationService.refreshToken(request));
	}

}
