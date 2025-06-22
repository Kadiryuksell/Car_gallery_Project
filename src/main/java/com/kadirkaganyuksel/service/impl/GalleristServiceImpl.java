package com.kadirkaganyuksel.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;
import com.kadirkaganyuksel.dto.gallerist.DtoGalleristIU;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.model.Address;
import com.kadirkaganyuksel.model.Gallerist;
import com.kadirkaganyuksel.repository.AddressRepository;
import com.kadirkaganyuksel.repository.GalleristRepository;
import com.kadirkaganyuksel.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		
		Optional<Address> optionalAddress = addressRepository.findById(dtoGalleristIU.getAddressID());
		
		if(optionalAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressID().toString()));
		}
		
		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		gallerist.setAddress(optionalAddress.get());
		
		return gallerist;
	}
	
	private DtoAddress getGalleristAddress(Gallerist gallerist) {
		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
		return dtoAddress;
	}
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		
		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
		
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		
		DtoAddress dtoAddress = getGalleristAddress(savedGallerist);
	
		dtoGallerist.setAddress(dtoAddress);
		
		return dtoGallerist;
	}

	@Override
	public Boolean deleteGallerist(Long id) {
		Optional<Gallerist> optional = galleristRepository.findById(id);
		
		if(optional.isPresent()) {
			galleristRepository.delete(optional.get());
			return true;
		}
		
		return false;
	}

	@Override
	public DtoGallerist getByGalleristId(Long id) {
	
		DtoGallerist dtoGallerist = new DtoGallerist();
		
		Optional<Gallerist> optional = galleristRepository.findById(id);
		
		if(optional.isEmpty()) {
			
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
		Gallerist gallerist = optional.get();
		DtoAddress dtoAddress = getGalleristAddress(gallerist);
		
		BeanUtils.copyProperties(gallerist, dtoGallerist);
		dtoGallerist.setAddress(dtoAddress);
		
		return dtoGallerist;
	}
	
	private DtoAddress isAddress(DtoGalleristIU dtoGalleristIU) {
		DtoAddress dtoAddress = new DtoAddress();
		
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressID());
		
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressID().toString()));
		}
		
		Address address = optAddress.get();
		BeanUtils.copyProperties(address, dtoAddress);
		
		return dtoAddress;
	}
	
	
	private Address dtoAddressToAddress(DtoAddress dtoAddress) {
	    
		 Optional<Address> optAddress = addressRepository.findById(dtoAddress.getId());
		 Address address = optAddress.get();
		 
		 BeanUtils.copyProperties(dtoAddress, address);
	
		 return address;
	}
	
	@Override
	public DtoGallerist updateGallerist(Long id,DtoGalleristIU dtoGalleristIU) {
		
		try {
			DtoGallerist dtoGallerist = new DtoGallerist();
			
			Optional<Gallerist> optional = galleristRepository.findById(id);
		
			if(optional.isEmpty()) {
				throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
			}
		
			Gallerist gallerist = optional.get();
			
			DtoAddress dtoAddress = isAddress(dtoGalleristIU);
			Address existingAddress = dtoAddressToAddress(dtoAddress);
		
			gallerist.setFirstName(dtoGalleristIU.getFirstName());
			gallerist.setLastName(dtoGalleristIU.getLastName());
			gallerist.setAddress(existingAddress);

			Gallerist savedGallerist = galleristRepository.save(gallerist);
			
			BeanUtils.copyProperties(savedGallerist, dtoGallerist);
			dtoGallerist.setAddress(dtoAddress);
			
			return dtoGallerist;
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.DUPLICATE_ID_KEY, e.getMessage()));
		}
	}

}
