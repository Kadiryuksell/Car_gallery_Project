package com.kadirkaganyuksel.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	
	NO_RECORD_EXIST("1004","Kayıt bulunamadı."),
	TOKEN_IS_EXPIRED("1005","Tokenın süresi bitmiştir."),
	USERNAME_NOT_FOUND("1006","Username bulunamadı."),
	USERNAME_OR_PASSWORD_INVALID("1007","Kullanıcı adı veya şifre hatalı."),
	REFRESH_TOKEN_NOT_FOUND("1008","Refresh Token bulunamadı."),
	REFRESH_TOKEN_IS_EXPİRED("1009","Refresh Tokenın süresi bitmiştir."),
	DUPLICATE_ID_KEY("1010","Duplicate key hatası."),
	CURRENCY_RATE_OCCURED("1011","Kur çekimi başarısız."),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","Müşterinin parası yeterli değil."),
	CAR_STATUS_IS_ALREADY_SALED("1012","Araba çoktan satılmıştır."),
	GENERAL_EXCEPTION("9999","Genel bir hata oluştu.");
	
	
	 private String code;
	 
	 private String message;
	 
	 MessageType(String code, String message) {
		 this.code = code;
		 this.message = message;
	 }
}
