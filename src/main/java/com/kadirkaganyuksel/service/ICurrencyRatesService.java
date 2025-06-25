package com.kadirkaganyuksel.service;

import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
	
}
