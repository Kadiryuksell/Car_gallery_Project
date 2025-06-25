package com.kadirkaganyuksel.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService{

	@Override
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
		
		try {
			String rootURL ="https://evds2.tcmb.gov.tr/service/evds/series=";
			String series = "TP.DK.USD.A.YTL";
			String type = "json";
			
			String endPoint = rootURL + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("key", "2jVrOaSGeu");
			
			HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(
					endPoint,
					HttpMethod.GET,
					httpEntity,
					new ParameterizedTypeReference<CurrencyRatesResponse>() {}
					);
			
			if(response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATE_OCCURED, e.getMessage()));
		}
		
		return null;
	}

}
