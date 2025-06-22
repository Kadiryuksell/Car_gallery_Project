package com.kadirkaganyuksel.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.customer.DtoCustomer;
import com.kadirkaganyuksel.dto.customer.DtoCustomerIU;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.model.Account;
import com.kadirkaganyuksel.model.Address;
import com.kadirkaganyuksel.model.Customer;
import com.kadirkaganyuksel.repository.AccountRepository;
import com.kadirkaganyuksel.repository.AddressRepository;
import com.kadirkaganyuksel.repository.CustomerRepository;
import com.kadirkaganyuksel.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {

		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressID());
		
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountID());
		
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressID().toString()));
		}
		
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountID().toString()));
		}
		
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCustomerIU, customer);

		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		
		return customer;
	}
	
	private DtoAddress isAddress(DtoCustomerIU dtoCustomerIU) {
		DtoAddress dtoAddress = new DtoAddress();
		
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressID());
		
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressID().toString()));
		}
		
		Address address = optAddress.get();
		BeanUtils.copyProperties(address, dtoAddress);
		
		return dtoAddress;
	}
	
	private DtoAccount isAccount(DtoCustomerIU dtoCustomerIU) {
		DtoAccount dtoAccount = new DtoAccount();
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountID());
		
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountID().toString()));
		}
	
		Account account = optAccount.get();
		BeanUtils.copyProperties(account, dtoAccount);
	
		return dtoAccount;
	}
	
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		
		DtoAccount customerAccount = getCustomerAccount(savedCustomer);
		DtoAddress customerAddress = getCustomerAddress(savedCustomer);		
		
		dtoCustomer.setAddress(customerAddress);
		dtoCustomer.setAccount(customerAccount);
		
		return dtoCustomer;
	}


	@Override
	public boolean deleteCustomer(long id) {

	Optional<Customer> optional = customerRepository.findById(id);
	
	if(optional.isEmpty()) {
		return false;
	}
	
	Customer customer = optional.get();
	
	customerRepository.delete(customer);
	
	return true;
	}


	private DtoAddress getCustomerAddress(Customer customer) {
		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
		return dtoAddress;
	}
	
	private DtoAccount getCustomerAccount(Customer customer) {
		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
		return dtoAccount;
	}
	
	
	@Override
	public DtoCustomer getCustomerById(Long id) {
		DtoCustomer  dtoCustomer = new DtoCustomer();
		
		Optional<Customer> optional = customerRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()));
		}
		
		Customer customer = optional.get();
		
		DtoAccount customerAccount = getCustomerAccount(customer);
		DtoAddress customerAddress = getCustomerAddress(customer);
		BeanUtils.copyProperties(customer, dtoCustomer);
		
		dtoCustomer.setAccount(customerAccount);
		dtoCustomer.setAddress(customerAddress);
		
		return dtoCustomer;
	}
	
	private Address existingAddress(Long id, DtoAddress dtoAddress) {
	    
	    Optional<Address> optional = addressRepository.findById(id);
	    
	    if (optional.isPresent()) {
	        Address address = optional.get();

	        BeanUtils.copyProperties(dtoAddress, address);
	        return addressRepository.save(address);
	    } else {
	
	        Address address = new Address();
	        BeanUtils.copyProperties(dtoAddress, address);
	        return addressRepository.save(address);
	    }
	}
	
	private Account existingAccount(Long id, DtoAccount dtoAccount) {
	  
	    Optional<Account> optional = accountRepository.findById(id);
	    if (optional.isPresent()) {
	        Account account = optional.get();
	        BeanUtils.copyProperties(dtoAccount, account);
	        return accountRepository.save(account);
	    } else {
	        Account account = new Account();
	        BeanUtils.copyProperties(dtoAccount, account);
	        return accountRepository.save(account);
	    }
	}
	@Override
	public DtoCustomer UpdateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
		try {
			DtoCustomer dtoCustomer = new DtoCustomer();

			Optional<Customer> optional = customerRepository.findById(id);
			
			if(optional.isEmpty()) {
				throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()));
			}
			
			//dtoAddress and dtoAccount 
			DtoAddress dtoAddress = isAddress(dtoCustomerIU);
			DtoAccount dtoAccount = isAccount(dtoCustomerIU);
			
			//Customer update
			Account account = existingAccount(dtoCustomerIU.getAccountID(),dtoAccount);
			Address address = existingAddress(dtoCustomerIU.getAddressID(),dtoAddress);
			
			Customer customer = optional.get();
			customer.setBirthOfDate(dtoCustomerIU.getBirthOfDate());
			customer.setFirstName(dtoCustomerIU.getFirstName());
			customer.setLastName(dtoCustomerIU.getLastName());
			customer.setTckn(dtoCustomerIU.getTckn());
			customer.setAddress(address);
			customer.setAccount(account);
			
			Customer savedCustomer = customerRepository.save(customer);
					
			BeanUtils.copyProperties(savedCustomer, dtoCustomer);
			dtoCustomer.setAddress(dtoAddress);
			dtoCustomer.setAccount(dtoAccount);
			
			return dtoCustomer;
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.DUPLICATE_ID_KEY, e.getMessage()));
		}
	}
}
