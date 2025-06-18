package com.kadirkaganyuksel.controller.AddressController;

import java.util.List;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
	public void deleteAddress(Long id);
	
	public RootEntity<List<DtoAddress>> getAllAddress();
	
	public RootEntity<DtoAddress>  getbyAddressId(long id);
	
	public RootEntity<DtoAddress>  updateAddress(long id, DtoAddressIU dtoAddressIU);
}
