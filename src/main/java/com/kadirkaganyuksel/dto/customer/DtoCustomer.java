package com.kadirkaganyuksel.dto.customer;

import java.util.Date;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.address.DtoAddress;

import lombok.Data;

@Data
public class DtoCustomer extends DtoBase{
	
	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date birthOfDate;
	
	private DtoAddress address;

	private DtoAccount account;
}
