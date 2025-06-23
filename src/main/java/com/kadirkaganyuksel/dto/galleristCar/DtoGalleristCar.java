package com.kadirkaganyuksel.dto.galleristCar;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;

import lombok.Data;

@Data
public class DtoGalleristCar extends DtoBase{

	private DtoGallerist gallerist;
	
	private DtoCar car;
}
