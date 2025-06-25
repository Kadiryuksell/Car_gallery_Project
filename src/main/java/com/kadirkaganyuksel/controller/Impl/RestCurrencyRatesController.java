package com.kadirkaganyuksel.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kadirkaganyuksel.controller.IRestCurrencyRatesController;
import com.kadirkaganyuksel.controller.RootEntity;
import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;
import com.kadirkaganyuksel.service.ICurrencyRatesService;

@RestController
@RequestMapping("rest/api")
public class RestCurrencyRatesController extends RestBaseController implements IRestCurrencyRatesController{

	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@GetMapping("/currency-rate")
	@Override
	public RootEntity<CurrencyRatesResponse> getCurrencyRatesResponse(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
	}

}
