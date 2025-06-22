package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;
import com.kadirkaganyuksel.dto.gallerist.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
	
	public Boolean deleteGallerist(Long id);
	
	public DtoGallerist getByGalleristId(Long id);
	
	public DtoGallerist updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);
	
}
