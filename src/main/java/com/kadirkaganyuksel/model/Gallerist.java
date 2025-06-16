package com.kadirkaganyuksel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallerist")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Gallerist extends BaseEntity{

	private String firstName;
	
	private String lastName;
	
	@OneToOne
	private Address address;
	
}
