package com.kadirkaganyuksel.dto.currencyRates;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyRatesResponse {

	private String totalCount;
	
	private List<CurrencyRatesItems> items;
	
}
