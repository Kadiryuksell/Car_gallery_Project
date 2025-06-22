package com.kadirkaganyuksel.dto.gallerist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoGalleristIU {
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private Long addressID;
}
