package com.kadirkaganyuksel.dto.gallerist;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.dto.address.DtoAddress;

import lombok.Data;

@Data
public class DtoGallerist extends DtoBase{
	
	private String firstName;
	
	private String lastName;
	
	private DtoAddress address;
}
