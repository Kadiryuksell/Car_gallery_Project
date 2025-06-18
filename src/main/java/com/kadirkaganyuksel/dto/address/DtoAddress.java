package com.kadirkaganyuksel.dto.address;

import com.kadirkaganyuksel.dto.DtoBase;

import lombok.Data;

@Data
public class DtoAddress extends DtoBase{

	private String city;
	
	private String district;
	
	private String neigbordhood;
	
	private String street;
	
}
