package com.kadirkaganyuksel.controller.SaledCarController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCar;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
	
	public RootEntity<Boolean> deleteSaledCar(Long id);
	
	public RootEntity<DtoSaledCar> findBySaledCarId(Long id);
	
	public RootEntity<DtoSaledCar> updateSaledCar(Long id,DtoSaledCarIU dtoSaledCarIU);
}
