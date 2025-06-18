package com.kadirkaganyuksel.dto.address;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DtoAddressIU {
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String district;
	
	@NotEmpty
	private String neigbordhood;
	
	@NotEmpty
	private String street;
}
