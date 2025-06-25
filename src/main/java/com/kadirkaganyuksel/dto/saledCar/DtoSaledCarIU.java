package com.kadirkaganyuksel.dto.saledCar;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoSaledCarIU {
	
	@NotNull
	private Long galleristID;
	
	@NotNull
	private Long carID;
	
	@NotNull
	private Long customerID;
}
