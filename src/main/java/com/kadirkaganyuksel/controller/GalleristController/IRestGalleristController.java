package com.kadirkaganyuksel.controller.GalleristController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;
import com.kadirkaganyuksel.dto.gallerist.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
	
	public RootEntity<Boolean> deleteGallerist(Long id);
	
	public RootEntity<DtoGallerist> getByGalleristId(Long id);
	
	public RootEntity<DtoGallerist> updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);
}