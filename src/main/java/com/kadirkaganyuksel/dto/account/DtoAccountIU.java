package com.kadirkaganyuksel.dto.account;

import java.math.BigDecimal;

import com.kadirkaganyuksel.dto.DtoBase;
import com.kadirkaganyuksel.enums.CurrencyType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoAccountIU extends DtoBase{
	@NotEmpty
	private String accountNo;
	
	@NotEmpty
	private String iban;
	
	@NotNull
	private BigDecimal amount;
	
	@NotNull
	private CurrencyType currencyType;
	
}
