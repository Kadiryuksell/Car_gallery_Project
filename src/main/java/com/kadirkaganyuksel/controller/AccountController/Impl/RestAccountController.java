package com.kadirkaganyuksel.controller.AccountController.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.controller.AccountController.IRestAccountController;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.account.DtoAccountIU;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;
import com.kadirkaganyuksel.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController extends RestBaseController  implements IRestAccountController {

	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteAccount(@PathVariable(name = "id") long id) {
		accountService.deleteAccount(id);
		
	}

	@GetMapping(path = "/{id}")
	@Override
	public RootEntity<DtoAccount> getByAccountId(@PathVariable(name = "id") Long id) {
		return ok(accountService.getByAccountId(id));
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@PathVariable(name = "id") long id,@Valid  @RequestBody DtoAccountIU dtoAccountIU) {
		
		return ok(accountService.updateAccount(id, dtoAccountIU));
	}

}
