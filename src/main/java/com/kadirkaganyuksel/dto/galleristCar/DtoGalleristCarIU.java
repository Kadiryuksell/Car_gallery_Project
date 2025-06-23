package com.kadirkaganyuksel.dto.galleristCar;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoGalleristCarIU {

	@NotNull
	private Long galleristID;
	
	@NotNull
	private Long carID;
	
}
