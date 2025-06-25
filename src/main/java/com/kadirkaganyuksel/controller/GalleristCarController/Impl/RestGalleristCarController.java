package com.kadirkaganyuksel.controller.GalleristCarController.Impl;

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
import com.kadirkaganyuksel.controller.GalleristCarController.IRestGalleristCarController;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCar;
import com.kadirkaganyuksel.dto.galleristCar.DtoGalleristCarIU;
import com.kadirkaganyuksel.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarController extends RestBaseController implements IRestGalleristCarController{

	@Autowired
	private IGalleristCarService galleristCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteGalleristCar(@PathVariable(name = "id") Long id) {
		
		return ok(galleristCarService.deleteGalleristCar(id));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoGalleristCar> getByGalleristCarId(@PathVariable(name = "id") Long id) {
		
		return ok(galleristCarService.getByGalleristCarId(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGalleristCar> updateGalleristCar(@PathVariable(name = "id") Long id,@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		
		return ok(galleristCarService.updateGalleristCar(id, dtoGalleristCarIU));
	}

	
	
}
