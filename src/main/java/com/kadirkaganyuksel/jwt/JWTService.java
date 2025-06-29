package com.kadirkaganyuksel.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Value("${jwt.key}")
	private String SECRET_KEY;
	
	public String generateToken(UserDetails userDetails) {
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*4))
				.signWith(getyKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {
		Claims claims = getClaims(token);
		return claimsFunc.apply(claims);
	}
	
	public String getUsernameByToken(String token) {
	return 	exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenValid(String token) {
		Date expiredDate = exportToken(token, Claims::getExpiration);
		return new Date().before(expiredDate);
	}

	public Claims getClaims(String token) {
		Claims claims = Jwts.parserBuilder()
		.setSigningKey(getyKey())
		.build()
		.parseClaimsJws(token).getBody();
	
		return claims;
	}
	
	public Key getyKey() {
		byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decode);
	}
	
}
