package com.kadirkaganyuksel.controller.CarController.Impl;

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
import com.kadirkaganyuksel.controller.CarController.IRestCarController;
import com.kadirkaganyuksel.controller.Impl.RestBaseController;
import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.car.DtoCarIU;
import com.kadirkaganyuksel.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarController extends RestBaseController implements IRestCarController{

	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		
		return ok(carService.saveCar(dtoCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteCar(@PathVariable(name = "id") Long id) {
		
		return ok(carService.deleteCar(id));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoCar> getByCarId(@PathVariable(name = "id") Long id) {
		
		return ok(carService.getByCarId(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@PathVariable(name = "id") Long id,@Valid @RequestBody DtoCarIU dtoCarIU) {
		
		return ok(carService.updateCar(id, dtoCarIU));
	}

}
