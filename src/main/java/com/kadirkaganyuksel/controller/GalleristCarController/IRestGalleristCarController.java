package com.kadirkaganyuksel.controller.GalleristCarController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCar;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCarIU;

public interface IRestGalleristCarController {
	
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
	public RootEntity<Boolean> deleteGalleristCar(Long id);
	
	public RootEntity<DtoGalleristCar> getByGalleristCarId(Long id);
	
	public RootEntity<DtoGalleristCar> updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);
}
