package com.kadirkaganyuksel.controller.CustomerController.Impl;

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
import com.kadirkaganyuksel.controller.CustomerController.IRestCustomerController;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.dto.customer.DtoCustomer;
import com.kadirkaganyuksel.dto.customer.DtoCustomerIU;
import com.kadirkaganyuksel.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController extends RestBaseController implements IRestCustomerController{

	@Autowired
	private ICustomerService customerService;
	
	@PostMapping(path = "/save")	
	@Override
	public RootEntity<DtoCustomer>  saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@DeleteMapping("delete/{id}")
	@Override
	public RootEntity<Boolean> deleteCustomer(@PathVariable(name = "id") long id) {
		
		return ok(customerService.deleteCustomer(id));
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public RootEntity<DtoCustomer> getCustomerById(@PathVariable(name = "id") Long id) {
		
		return ok(customerService.getCustomerById(id));
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public RootEntity<DtoCustomer> UpdateCustomer(@PathVariable(name = "id") Long id,@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {

		return ok(customerService.UpdateCustomer(id, dtoCustomerIU));
	}

}
