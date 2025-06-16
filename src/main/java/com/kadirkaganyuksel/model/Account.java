package com.kadirkaganyuksel.model;

import java.math.BigDecimal;

import com.kadirkaganyuksel.enums.CurrencyType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity{

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;
	
	
}
