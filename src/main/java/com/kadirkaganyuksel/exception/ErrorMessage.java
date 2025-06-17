package com.kadirkaganyuksel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

	private MessageType messageType;
	
	private String offStatic;
	
	public String prepareErrorMessage() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(messageType.getMessage());
		if(this.offStatic != null ) {
			builder.append(" : " + offStatic);
		}
		
		return builder.toString();
	}
	
}
