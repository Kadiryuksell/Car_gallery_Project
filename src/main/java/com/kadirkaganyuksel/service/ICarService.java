package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.car.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
	
	public Boolean deleteCar(Long id);
	
	public DtoCar getByCarId(Long id);
	
	public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);
	
}
