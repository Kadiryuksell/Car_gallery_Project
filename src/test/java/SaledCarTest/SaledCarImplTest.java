package SaledCarTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesItems;
import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCar;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCarIU;
import com.kadirkaganyuksel.enums.CarStatusType;
import com.kadirkaganyuksel.model.Account;
import com.kadirkaganyuksel.model.Address;
import com.kadirkaganyuksel.model.Car;
import com.kadirkaganyuksel.model.Customer;
import com.kadirkaganyuksel.model.Gallerist;
import com.kadirkaganyuksel.model.SaledCar;
import com.kadirkaganyuksel.repository.CarRepository;
import com.kadirkaganyuksel.repository.CustomerRepository;
import com.kadirkaganyuksel.repository.GalleristRepository;
import com.kadirkaganyuksel.repository.SaledCarRepository;
import com.kadirkaganyuksel.service.ICurrencyRatesService;
import com.kadirkaganyuksel.service.impl.SaledCarServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SaledCarImplTest {
	@Mock
    private SaledCarRepository saledCarRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private GalleristRepository galleristRepository;

    @Mock
    private CarRepository carRepository;
    
    @Mock
    private ICurrencyRatesService currencyRatesService;

    @InjectMocks
    private SaledCarServiceImpl saledCarService;
    
    @Test
    void testBuyCar_Success() {
        Long customerId = 1L, carId = 2L, galleristId = 3L;

        DtoSaledCarIU dtoInput = new DtoSaledCarIU();
        dtoInput.setCustomerID(customerId);
        dtoInput.setCarID(carId);
        dtoInput.setGalleristID(galleristId);
        

        Account account = new Account();
        account.setAmount(BigDecimal.valueOf(100000000));
        
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setAccount(account);
        customer.setCreateTime(new Date());
        
        Car car = new Car();
        car.setId(carId);
        car.setPrice(BigDecimal.valueOf(100));
        car.setCarStatusType(CarStatusType.SALABLE);
        car.setCreateTime(new Date());

        Gallerist gallerist = new Gallerist();
        gallerist.setId(galleristId);
        gallerist.setCreateTime(new Date());

        Address customerAddress = new Address();
        customerAddress.setId(10L);  
        customerAddress.setCity("Istanbul");
        customerAddress.setDistrict("Kadikoy");
        customerAddress.setStreet("Test Street");
        customerAddress.setCreateTime(new Date());

        Address galleristAddress = new Address();
        galleristAddress.setId(20L);
        galleristAddress.setCity("Ankara");
        galleristAddress.setDistrict("Cankaya");
        galleristAddress.setStreet("Gallery Road");
        galleristAddress.setCreateTime(new Date());

        customer.setAddress(customerAddress);
        gallerist.setAddress(galleristAddress);
        
        CurrencyRatesItems item = new CurrencyRatesItems();
        item.setUsd("40");
        CurrencyRatesResponse currencyRatesResponse = new CurrencyRatesResponse();
        currencyRatesResponse.setItems(List.of(item));
        
        SaledCar savedSaledCar = new SaledCar();
        savedSaledCar.setCar(car);
        savedSaledCar.setCustomer(customer);
        savedSaledCar.setGallerist(gallerist);
    
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(galleristRepository.findById(galleristId)).thenReturn(Optional.of(gallerist));
        when(currencyRatesService.getCurrencyRates(anyString(), anyString())).thenReturn(currencyRatesResponse);
        when(saledCarRepository.save(any())).thenReturn(savedSaledCar);
        when(carRepository.save(any())).thenReturn(car);
        when(customerRepository.save(any())).thenReturn(customer);

        DtoSaledCar result = saledCarService.buyCar(dtoInput);

        assertNotNull(result);
        verify(saledCarRepository).save(any());
        verify(carRepository).save(any());
        verify(customerRepository).save(any());
        assertEquals(CarStatusType.SALED, car.getCarStatusType());
        assertNotNull(customer.getAccount().getAmount());
        
        System.out.println("BuyCar başarılı. " + result);
    }

    
}
