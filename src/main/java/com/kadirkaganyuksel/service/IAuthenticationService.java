package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.AuthResponse;
import com.kadirkaganyuksel.dto.DtoUser;
import com.kadirkaganyuksel.dto.RefreshTokenRequest;
import com.kadirkaganyuksel.jwt.AuthRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}
