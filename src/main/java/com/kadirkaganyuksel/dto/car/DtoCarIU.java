package com.kadirkaganyuksel.dto.car;

import java.math.BigDecimal;

import com.kadirkaganyuksel.enums.CarStatusType;
import com.kadirkaganyuksel.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCarIU {
	
	@NotNull
	private String numberPlate;
	@NotNull
	private String brand;
	@NotNull
	private String model;
	@NotNull
	private Integer productionYear;
	@NotNull
	private BigDecimal price;
	@NotNull
	private CurrencyType currencyType;
	@NotNull
	private BigDecimal damagePrice;
	@NotNull
	private CarStatusType carStatusType;
}
