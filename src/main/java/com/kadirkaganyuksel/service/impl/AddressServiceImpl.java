package com.kadirkaganyuksel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;
import com.kadirkaganyuksel.model.Address;
import com.kadirkaganyuksel.repository.AddressRepository;
import com.kadirkaganyuksel.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	
	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		address.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoAddressIU, address);
		
		return address;
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();
		
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		
		return dtoAddress;
	}

	@Override
	public void deleteAddress(Long id) {
	Optional<Address> optional = addressRepository.findById(id);
	
	if(optional.isPresent()) {
		addressRepository.delete(optional.get());
		}
	
	}

	@Override
	public List<DtoAddress> getAllAddress() {
		
		List<DtoAddress> dtoAddressList = new ArrayList<>();
		
		List<Address> addressList = addressRepository.findAll();
		
		for (Address address : addressList) {
			DtoAddress dtoAddress = new DtoAddress();
			BeanUtils.copyProperties(address, dtoAddress);
			
			dtoAddressList.add(dtoAddress);
		}
		
		
		return dtoAddressList;
	}
	
	@Override
	public DtoAddress getbyAddressId(long id) {
		
		DtoAddress dtoAddress = new DtoAddress();
		
		Optional<Address> optionalAddress = addressRepository.findById(id);
		
		if(optionalAddress.isEmpty()) {
			return null;
		}
		
		BeanUtils.copyProperties(optionalAddress.get(), dtoAddress);
		
		return dtoAddress;
	}

	@Override
	public DtoAddress updateAddress(long id, DtoAddressIU dtoAddressIU) {
		
		Optional<Address> optionalAddress = addressRepository.findById(id);
		
		DtoAddress dtoAddress = new DtoAddress();
		
		if(optionalAddress.isEmpty()) {
			return null;
		}
			
		Address dbAddress = optionalAddress.get();
		
		dbAddress.setCity(dtoAddressIU.getCity());
		dbAddress.setDistrict(dtoAddressIU.getDistrict());
		dbAddress.setNeigbordhood(dtoAddressIU.getNeigbordhood());
		dbAddress.setStreet(dtoAddressIU.getStreet());

		Address updateAddress = addressRepository.save(dbAddress);
		
		BeanUtils.copyProperties(updateAddress, dtoAddress);
		
		return dtoAddress;
	}
	
}
