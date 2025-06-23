package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCar;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
	public Boolean deleteGalleristCar(Long id);
	
	public DtoGalleristCar getByGalleristCarId(Long id);
	
	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);
	
}
