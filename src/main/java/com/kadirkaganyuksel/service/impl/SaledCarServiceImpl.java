package com.kadirkaganyuksel.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadirkaganyuksel.dto.account.DtoAccount;
import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.car.DtoCar;
import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;
import com.kadirkaganyuksel.dto.customer.DtoCustomer;
import com.kadirkaganyuksel.dto.gallerist.DtoGallerist;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCar;
import com.kadirkaganyuksel.dto.saledCar.DtoSaledCarIU;
import com.kadirkaganyuksel.enums.CarStatusType;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.model.Car;
import com.kadirkaganyuksel.model.Customer;
import com.kadirkaganyuksel.model.SaledCar;
import com.kadirkaganyuksel.repository.CarRepository;
import com.kadirkaganyuksel.repository.CustomerRepository;
import com.kadirkaganyuksel.repository.GalleristRepository;
import com.kadirkaganyuksel.repository.SaledCarRepository;
import com.kadirkaganyuksel.service.ICurrencyRatesService;
import com.kadirkaganyuksel.service.ISaledCarService;
import com.kadirkaganyuksel.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService{

	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	private BigDecimal converCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		
	    BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
	    BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd,2,RoundingMode.HALF_UP);
	    
	    return customerUSDAmount;
	}
	
	
	private boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerID());
	
		if(optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerID().toString()));
		}
	
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarID());
		
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarID().toString()));
		}
		
		BigDecimal customerUSDAmount = converCustomerAmountToUSD(optCustomer.get());
		
		if(customerUSDAmount.compareTo(optCar.get().getPrice()) >= 0) {
			return true;
		}
		
		return false;
	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerID()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristID()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarID()).orElse(null));
	
		return saledCar;
	}
	
	
	private boolean checkCarStatus(Long carId) {
		Optional<Car> optional = carRepository.findById(carId);
	
		if(optional.isPresent() && optional.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
	
		return true;
	}
	
	private BigDecimal remaningCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = converCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		return remaningCustomerUSDAmount.multiply(usd);
	}
	
	
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		
		
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH,""));
		}
		
		if(!checkCarStatus(dtoSaledCarIU.getCarID())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarID().toString()));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		
		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		return toDTO(savedSaledCar);
	}

	
	private DtoSaledCar toDTO(SaledCar saledCar) {
		
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoGalleristAddress = new DtoAddress();
		DtoAddress dtoCustomerAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
		DtoCar dtoCar = new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCustomer().getAddress(), dtoCustomerAddress);
		BeanUtils.copyProperties(saledCar.getGallerist().getAddress(), dtoGalleristAddress);
		BeanUtils.copyProperties(saledCar.getCustomer().getAccount(), dtoAccount);
		
		dtoCustomer.setAddress(dtoCustomerAddress);
		dtoCustomer.setAccount(dtoAccount);
		dtoGallerist.setAddress(dtoGalleristAddress);
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);
		
		return dtoSaledCar;
		
	}
	
}
