package com.kadirkaganyuksel.dto.customer;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCustomerIU {

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String tckn;
	@NotNull
	private Date birthOfDate;
	@NotNull
	private Long addressID;
	@NotNull
	private Long accountID;
	
}
