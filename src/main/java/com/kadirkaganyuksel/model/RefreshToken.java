package com.kadirkaganyuksel.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refresh_token")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken extends BaseEntity{

	private String refreshToken;
	
	private Date expiredDate;
	
	@ManyToOne
	private User user;
	
}
