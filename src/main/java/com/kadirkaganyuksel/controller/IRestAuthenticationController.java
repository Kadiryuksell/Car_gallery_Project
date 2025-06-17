package com.kadirkaganyuksel.controller;

import com.kadirkaganyuksel.dto.AuthResponse;
import com.kadirkaganyuksel.dto.DtoUser;
import com.kadirkaganyuksel.dto.RefreshTokenRequest;
import com.kadirkaganyuksel.jwt.AuthRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RootEntity<AuthResponse> resfreshToken(RefreshTokenRequest request);
}
