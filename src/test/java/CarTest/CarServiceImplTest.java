package CarTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.car.DtoCarIU;
import com.kadirkaganyuksel.enums.CarStatusType;
import com.kadirkaganyuksel.enums.CurrencyType;
import com.kadirkaganyuksel.model.Car;
import com.kadirkaganyuksel.repository.CarRepository;
import com.kadirkaganyuksel.service.impl.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

	@Mock
	private CarRepository carRepository;
	
	@InjectMocks
	private CarServiceImpl carService;
	
	@Test
	public void testSaveCar() {
		 DtoCarIU dtoInput = new DtoCarIU();
	        dtoInput.setBrand("Toyota");
	        dtoInput.setModel("Corolla");
	        dtoInput.setNumberPlate("34ABC123");
	        dtoInput.setPrice(new BigDecimal(7000));
	        dtoInput.setProductionYear(2015);
	        dtoInput.setCurrencyType(CurrencyType.USD);
	        dtoInput.setCarStatusType(CarStatusType.SALABLE);
	        dtoInput.setDamagePrice(new BigDecimal(1000));
	        
	        Car savedCar = new Car();
	        savedCar.setId(1L);
	        savedCar.setBrand(dtoInput.getBrand());
	        savedCar.setModel(dtoInput.getModel());
	        savedCar.setNumberPlate(dtoInput.getNumberPlate());
	        savedCar.setPrice(dtoInput.getPrice());
	        savedCar.setProductionYear(dtoInput.getProductionYear());
	        savedCar.setCurrencyType(dtoInput.getCurrencyType());
	        savedCar.setCarStatusType(dtoInput.getCarStatusType());
	        savedCar.setDamagePrice(dtoInput.getDamagePrice());
	        savedCar.setCreateTime(new Date());
	        
	         when(carRepository.save(any(Car.class))).thenReturn(savedCar);
	         
	         DtoCar result = carService.saveCar(dtoInput);
	         
	         assertNotNull(result);
	         assertEquals("Toyota", result.getBrand());
	         assertEquals("Corolla", result.getModel());
	         verify(carRepository, times(1)).save(any(Car.class));
	         System.out.println("SaveCar  başarılı " + result);
	}
	
	@Test
	public void testDeleteCar() {
		Long id = 1L;
		Car car = new Car();
		car.setId(id);
		
		when(carRepository.findById(id)).thenReturn(Optional.of(car));
		
		Boolean result = carService.deleteCar(id);
		assertTrue(result);
		verify(carRepository,times(1)).delete(car);
		System.out.println("DeleteCar başarılı");
		
	}
	
	@Test
	public void testGetByCarId() {
		Long id = 1L;
		Car car = new Car();
		car.setId(id);
		car.setBrand("Honda");
		car.setModel("Civic");
		
		when(carRepository.findById(id)).thenReturn(Optional.of(car));
		
		DtoCar dtoCar = carService.getByCarId(id);
	
		assertNotNull(dtoCar);
		assertEquals("Honda", dtoCar.getBrand());
		assertEquals("Civic", dtoCar.getModel());
		System.out.println("GetByCarId başarılı. " + dtoCar);
	}
	
	@Test
	public void testUpdateCar() {
		Long id = 1L;
		DtoCarIU dtoInput = new DtoCarIU();
		 dtoInput.setBrand("BMW");
	        dtoInput.setModel("X5");
	        dtoInput.setNumberPlate("06XYZ999");
	        dtoInput.setPrice(new BigDecimal(5000));
	        dtoInput.setProductionYear(2020);
	        dtoInput.setCurrencyType(CurrencyType.USD);
	        dtoInput.setCarStatusType(CarStatusType.SALABLE);
	        dtoInput.setDamagePrice(new BigDecimal(1000));
	        
	        Car existingCar = new Car();
	        existingCar.setId(id);
	        existingCar.setBrand("OldBrand");
	        
	        when(carRepository.findById(id)).thenReturn(Optional.of(existingCar));
	        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));
	        
	        DtoCar result = carService.updateCar(id, dtoInput);
	        
	        assertNotNull(result);
	        assertEquals("BMW", result.getBrand());
	        assertEquals("X5", result.getModel());
	        verify(carRepository).save(existingCar);
	        
	        System.out.println("UpdateCar başarılı. " + result);
	}
	
}
