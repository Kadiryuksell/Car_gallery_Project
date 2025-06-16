package com.kadirkaganyuksel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "address")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {

	private String city;
	
	private String district;
	
	private String neigbordhood;
	
	private String street;
}
