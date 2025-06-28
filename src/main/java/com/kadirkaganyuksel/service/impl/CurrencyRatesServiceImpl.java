package com.kadirkaganyuksel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.kadirkaganyuksel.dto.currencyRates.CurrencyRatesResponse;
import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.service.ICurrencyRatesService;

import reactor.core.publisher.Mono;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService{

	@Autowired
	private WebClient webClient;
	
	
	@Value("${tcmb.api.root-url}")
    private String rootURL;

    @Value("${tcmb.api.series}")
    private String series;

    @Value("${tcmb.api.response-type}")
    private String type;

    @Value("${tcmb.api.key}")
    private String apiKey;
	
	
	@Override
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
		
		try {
			
			String endPoint = rootURL + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

			return webClient
                    .get()
                    .uri(endPoint)
                    .header("key", apiKey)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse ->
                            clientResponse.bodyToMono(String.class)
                                    .flatMap(errorBody ->
                                            Mono.error(new BaseException(
                                                    new ErrorMessage(MessageType.CURRENCY_RATE_OCCURED, errorBody)
                                            ))
                                    )
                    )
                    .bodyToMono(CurrencyRatesResponse.class)
                    .block(); 
			
		}catch (WebClientResponseException ex) {
			throw new BaseException(new ErrorMessage(
                    MessageType.CURRENCY_RATE_OCCURED,
                    "HTTP Error: " + ex.getStatusCode().value() + " - " + ex.getResponseBodyAsString()
            ));
		} 
		catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATE_OCCURED, e.getMessage()));
		}
	}

}
