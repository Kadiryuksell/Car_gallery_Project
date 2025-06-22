package com.kadirkaganyuksel.dto.account;

import java.math.BigDecimal;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoAccount extends DtoBase{

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
	
}
