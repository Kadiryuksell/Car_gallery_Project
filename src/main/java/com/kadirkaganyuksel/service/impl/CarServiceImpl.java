package com.kadirkaganyuksel.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.car.DtoCarIU;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.model.Car;
import com.kadirkaganyuksel.repository.CarRepository;
import com.kadirkaganyuksel.service.ICarService;

@Service
public class CarServiceImpl implements ICarService{

	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoCarIU, car);
		
		return car;
	}
	
	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		DtoCar dtoCar = new DtoCar();
		Car savedCar = carRepository.save(createCar(dtoCarIU));
		BeanUtils.copyProperties(savedCar, dtoCar);
		return dtoCar;
	}

	@Override
	public Boolean deleteCar(Long id) {
		Optional<Car> optional = carRepository.findById(id);
		
		if(optional.isEmpty()) {
			return false;
		}
		
		carRepository.delete(optional.get());
		return true;
	}

	@Override
	public DtoCar getByCarId(Long id) {
		DtoCar dtoCar = new DtoCar();
		
		Optional<Car> optional = carRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, id.toString()));
		}
		
		Car car = optional.get();
		BeanUtils.copyProperties(car, dtoCar);
		return dtoCar;
	}

	@Override
	public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
		DtoCar dtoCar = new DtoCar();
		
		Optional<Car> optionalCar = carRepository.findById(id);
		
		if(optionalCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, id.toString()));
		}
		
		Car car = optionalCar.get();
		
		car.setBrand(dtoCarIU.getBrand());
		car.setCarStatusType(dtoCarIU.getCarStatusType());
		car.setCurrencyType(dtoCarIU.getCurrencyType());
		car.setDamagePrice(dtoCarIU.getDamagePrice());
		car.setModel(dtoCarIU.getModel());
		car.setNumberPlate(dtoCarIU.getNumberPlate());
		car.setPrice(dtoCarIU.getPrice());
		car.setProductionYear(dtoCarIU.getProductionYear());
		
		Car savedCar = carRepository.save(car);
		BeanUtils.copyProperties(savedCar, dtoCar);
		return dtoCar;
	}

}
