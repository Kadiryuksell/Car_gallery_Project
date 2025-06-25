package com.kadirkaganyuksel.controller;

import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

	public RootEntity<CurrencyRatesResponse> getCurrencyRatesResponse(String startDate, String endDate);
}
