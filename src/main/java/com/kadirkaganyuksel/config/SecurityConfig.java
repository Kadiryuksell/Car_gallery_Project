package com.kadirkaganyuksel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kadirkaganyuksel.handler.AuthEntryPoint;
import com.kadirkaganyuksel.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String REGISTER ="/register";
	public static final String AUTHENTICATE ="/authenticate";
	public static final String REFRESH_TOKEN ="/refreshToken";
	public static final String[] SWAGGER_PATHS = {
			"/swagger-ui/**",
			"/v3/api-docs/**",
			"/swagger-ui.html"
	};

	private final AuthenticationProvider authenticationProvider;
	private final JWTAuthenticationFilter jwtAuthenticationFilter;
	private final AuthEntryPoint authEntryPoint;
	
	public SecurityConfig(AuthenticationProvider authenticationProvider,JWTAuthenticationFilter jwtAuthenticationFilter,AuthEntryPoint authEntryPoint) {
		this.authenticationProvider = authenticationProvider;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authEntryPoint = authEntryPoint;
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
	    .csrf(csrf -> csrf.disable()) 
	    .authorizeHttpRequests(request -> request
	        .requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN).permitAll()
	        .requestMatchers(SWAGGER_PATHS).permitAll()
	        .anyRequest().authenticated()
	    )
	    .exceptionHandling(exception -> exception
	        .authenticationEntryPoint(authEntryPoint)
	    )
	    .sessionManagement(session -> session
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    )
	    .authenticationProvider(authenticationProvider)
	    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();

		
		
	/*	http.csrf().disable()
		.authorizeHttpRequests(request -> request.requestMatchers(REGISTER,AUTHENTICATE,REFRESH_TOKEN)
				.permitAll()
				.requestMatchers(SWAGGER_PATHS).permitAll()
				.anyRequest()
				.authenticated())
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	
		return http.build(); */
	}
	
}
