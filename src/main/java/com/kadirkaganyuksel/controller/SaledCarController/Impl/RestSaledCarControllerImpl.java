package com.kadirkaganyuksel.controller.SaledCarController.Impl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.controller.SaledCarController.IRestSaledCarController;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCar;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCarIU;
import com.kadirkaganyuksel.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController{

	
	private ISaledCarService saledCarService;
	
	public RestSaledCarControllerImpl(ISaledCarService saledCarService) {
		this.saledCarService = saledCarService;
	}
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}


	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteSaledCar(@PathVariable Long id) {
		
		return ok(saledCarService.deleteSaledCar(id));
	}


	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoSaledCar> findBySaledCarId(@PathVariable Long id) {
		
		return ok(saledCarService.findBySaledCarId(id));
	}


	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoSaledCar> updateSaledCar(@PathVariable Long id,@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		
		return ok(saledCarService.updateSaledCar(id, dtoSaledCarIU));
	}

}
