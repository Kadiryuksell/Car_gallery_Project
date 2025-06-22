package com.kadirkaganyuksel.controller.AccountController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.account.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
	
	public void deleteAccount(long id);
	
	public RootEntity<DtoAccount> getByAccountId(Long id);
	
	public RootEntity<DtoAccount> updateAccount(long id, DtoAccountIU dtoAccountIU);
	
}
