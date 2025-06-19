package com.kadirkaganyuksel.dto.account;

import java.math.BigDecimal;

import com.kadirkaganyuksel.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoAccount {

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
	
}
