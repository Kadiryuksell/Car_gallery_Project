package com.kadirkaganyuksel.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.account.DtoAccountIU;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;
import com.kadirkaganyuksel.model.Account;
import com.kadirkaganyuksel.repository.AccountRepository;
import com.kadirkaganyuksel.service.IAccountService;


@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	private Account createAccount(DtoAccountIU dtoAccountIU) {
		
		Account account = new Account();
		account.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoAccountIU, account);
		
		return account;
	}
	
	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		
		DtoAccount dtoAccount = new DtoAccount();
		
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
		
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		
		return dtoAccount;
	}

	@Override
	public void deleteAccount(long id) {
		
		Optional<Account> deleteAccount = accountRepository.findById(id);
		
		if(deleteAccount.isPresent()) {
			
			accountRepository.delete(deleteAccount.get());
		}
	}
	
	private Account findAccount(long id) {
		
		Optional<Account> optional = accountRepository.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}
		
		return optional.get();	
	}
	

	@Override
	public DtoAccount getByAccountId(Long id) {
		
		DtoAccount dtoAccount = new DtoAccount();
		
		Account account = findAccount(id);
		
		BeanUtils.copyProperties(account, dtoAccount);
		
		return dtoAccount;
	}

	@Override
	public DtoAccount updateAccount(long id, DtoAccountIU dtoAccountIU) {
		
		DtoAccount dtoAccount = new DtoAccount();
		
		Account account = findAccount(id);
		
		account.setAccountNo(dtoAccountIU.getAccountNo());
		account.setAmount(dtoAccountIU.getAmount());
		account.setCurrencyType(dtoAccountIU.getCurrencyType());
		account.setIban(dtoAccountIU.getIban());
		
			Account savedAccount = accountRepository.save(account);
		
			BeanUtils.copyProperties(savedAccount, dtoAccount);
			
		return dtoAccount;
	}
	
}
