package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.saledCar.DtoSaledCar;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
	
	public Boolean deleteSaledCar(Long id);
	
	public DtoSaledCar findBySaledCarId(Long id);
	
	public DtoSaledCar updateSaledCar(Long id,DtoSaledCarIU dtoSaledCarIU);
	
}
