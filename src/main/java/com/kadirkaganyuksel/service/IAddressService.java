package com.kadirkaganyuksel.service;

import java.util.List;

import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public void deleteAddress(Long id);
	
	public  List<DtoAddress> getAllAddress();
	
	public DtoAddress getbyAddressId(long id);
	
	public DtoAddress updateAddress(long id, DtoAddressIU dtoAddressIU);
}
