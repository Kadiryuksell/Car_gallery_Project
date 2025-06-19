package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.account.DtoAccountIU;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
	
	public void deleteAccount(long id);
	
	public DtoAccount getByAccountId(Long id);
	
	public DtoAccount updateAccount(long id, DtoAccountIU dtoAccountIU);
	
}
