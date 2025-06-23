package com.kadirkaganyuksel.dto.car;

import java.math.BigDecimal;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.enums.CarStatusType;
import com.kadirkaganyuksel.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoCar extends DtoBase{

	private String numberPlate;

	private String brand;

	private String model;

	private Integer productionYear;

	private BigDecimal price;

	private CurrencyType currencyType;

	private BigDecimal damagePrice;

	private CarStatusType carStatusType;
	
}
