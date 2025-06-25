package com.kadirkaganyuksel.dto.saledCar;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.customer.DtoCustomer;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;

import lombok.Data;

@Data
public class DtoSaledCar extends DtoBase{

	private DtoGallerist gallerist;
	
	private DtoCar car;

	private DtoCustomer customer;
}
