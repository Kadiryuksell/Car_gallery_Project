package com.kadirkaganyuksel.controller.CustomerController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.customer.DtoCustomer;
import com.kadirkaganyuksel.dto.customer.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public RootEntity<Boolean> deleteCustomer(long id);
	
	public RootEntity<DtoCustomer> getCustomerById(Long id);
	
	public  RootEntity<DtoCustomer> UpdateCustomer(Long id,DtoCustomerIU dtoCustomerIU);
}
