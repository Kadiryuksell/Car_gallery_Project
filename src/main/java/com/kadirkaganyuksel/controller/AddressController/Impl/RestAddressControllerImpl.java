package com.kadirkaganyuksel.controller.AddressController.Impl;

import java.util.List;

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
import com.kadirkaganyuksel.controller.AddressController.IRestAddressController;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;
import com.kadirkaganyuksel.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{


	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		
		return ok(addressService.saveAddress(dtoAddressIU));
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteAddress(@PathVariable(name = "id") Long id) {
		addressService.deleteAddress(id);
		
	}

	@GetMapping(path = "/list")
	@Override
	public RootEntity<List<DtoAddress>> getAllAddress() {
		
		return ok(addressService.getAllAddress()); 
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public RootEntity<DtoAddress> getbyAddressId(@PathVariable(name = "id") long id) {
		
		return ok(addressService.getbyAddressId(id)); 
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public RootEntity<DtoAddress> updateAddress(@PathVariable(name = "id") long id, @Valid  @RequestBody DtoAddressIU dtoAddressIU) {
		
		return ok(addressService.updateAddress(id, dtoAddressIU));
	}

}
