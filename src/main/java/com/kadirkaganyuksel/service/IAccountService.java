package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.account.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
	
	public void deleteAccount(long id);
	
	public DtoAccount getByAccountId(Long id);
	
	public DtoAccount updateAccount(long id, DtoAccountIU dtoAccountIU);
	
}
