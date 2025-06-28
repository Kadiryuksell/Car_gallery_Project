package com.kadirkaganyuksel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static String getCurrentDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return date.format(formatter);
	}
}
