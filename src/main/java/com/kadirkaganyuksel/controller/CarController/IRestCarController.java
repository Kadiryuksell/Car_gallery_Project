package com.kadirkaganyuksel.controller.CarController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.car.DtoCarIU;

public interface IRestCarController {
	
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	
	public RootEntity<Boolean> deleteCar(Long id);
	
	public RootEntity<DtoCar> getByCarId(Long id);
	
	public RootEntity<DtoCar> updateCar(Long id, DtoCarIU dtoCarIU);
}
