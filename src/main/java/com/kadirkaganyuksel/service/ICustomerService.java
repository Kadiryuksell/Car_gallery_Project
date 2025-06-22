package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.customer.DtoCustomer;
import com.kadirkaganyuksel.dto.customer.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public boolean deleteCustomer(long id);
	
	public DtoCustomer getCustomerById(Long id);
	
	public DtoCustomer UpdateCustomer(Long id,DtoCustomerIU dtoCustomerIU);
	
}
