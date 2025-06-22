package com.kadirkaganyuksel.controller.GalleristController.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.controller.GalleristController.IRestGalleristController;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;
import com.kadirkaganyuksel.dto.gallerist.DtoGalleristIU;
import com.kadirkaganyuksel.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController{

	@Autowired
	private IGalleristService  galleristService;

	@PostMapping(path = "/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		
		return ok(galleristService.saveGallerist(dtoGalleristIU)); 
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteGallerist(@PathVariable(name = "id") Long id) {
		
		return ok(galleristService.deleteGallerist(id)); 
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoGallerist> getByGalleristId(@PathVariable(name = "id") Long id) {
		
		return ok(galleristService.getByGalleristId(id)); 
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGallerist> updateGallerist(@PathVariable(name = "id") Long id,@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		
		return ok(galleristService.updateGallerist(id, dtoGalleristIU));
	}
	
	
	
}
