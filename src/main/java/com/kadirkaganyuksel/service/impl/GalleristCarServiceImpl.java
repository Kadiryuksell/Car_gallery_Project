package com.kadirkaganyuksel.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCar;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCarIU;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.model.Car;
import com.kadirkaganyuksel.model.Gallerist;
import com.kadirkaganyuksel.model.GalleristCar;
import com.kadirkaganyuksel.repository.CarRepository;
import com.kadirkaganyuksel.repository.GalleristCarRepository;
import com.kadirkaganyuksel.repository.GalleristRepository;
import com.kadirkaganyuksel.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService{

	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristID());
		
		if(optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, dtoGalleristCarIU.getGalleristID().toString()));
		}
		
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarID());
		
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, dtoGalleristCarIU.getCarID().toString()));
		}
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
		
		return galleristCar;
	}

	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoAddress dtoAddress = new DtoAddress();
		DtoCar dtoCar = new DtoCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setGallerist(dtoGallerist);
		dtoGalleristCar.setCar(dtoCar);
		
		return dtoGalleristCar;
	}

	@Override
	public Boolean deleteGalleristCar(Long id) {
		Optional<GalleristCar> optional = galleristCarRepository.findById(id);
		
		if (optional.isEmpty()) {
			return false;
		}
		
		galleristCarRepository.delete(optional.get());
		return true;
	}

	@Override
	public DtoGalleristCar getByGalleristCarId(Long id) {
		
		Optional<GalleristCar> optional = galleristCarRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, id.toString()));
		}
		
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoAddress dtoAddress = new DtoAddress();
		DtoCar dtoCar = new DtoCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
	
		GalleristCar galleristCar = optional.get();
	
		
		BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
		BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
		BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setCar(dtoCar);
		dtoGalleristCar.setGallerist(dtoGallerist);
		
		return dtoGalleristCar;
	}

	@Override
	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {
		try {
			Optional<GalleristCar> optional = galleristCarRepository.findById(id);
			
			if(optional.isEmpty()) {
				throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, id.toString()));
			}
			
			Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarID());
			
			if(optCar.isEmpty()) {
				throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, dtoGalleristCarIU.getGalleristID().toString()));
			}
			
			Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristID());
			
			if(optGallerist.isEmpty()) {
				throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, dtoGalleristCarIU.getGalleristID().toString()));
			}
			
			GalleristCar galleristCar = optional.get();
			galleristCar.setCar(optCar.get());
			galleristCar.setGallerist(optGallerist.get());
			
			GalleristCar savedGalleristCar = galleristCarRepository.save(galleristCar);
			
			DtoAddress dtoAddress = new DtoAddress();
			DtoCar dtoCar = new DtoCar();
			DtoGallerist dtoGallerist = new DtoGallerist();
			DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
			
			BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
			BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
			BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
			
			dtoGallerist.setAddress(dtoAddress);
			dtoGalleristCar.setCar(dtoCar);
			dtoGalleristCar.setGallerist(dtoGallerist);
			
			return dtoGalleristCar;
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.DUPLICATE_ID_KEY, e.getMessage()));
		}
		
	}
	
	
	

}
