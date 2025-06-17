package com.kadirkaganyuksel.service.impl;

import com.kadirkaganyuksel.exception.BaseException;
import com.kadirkaganyuksel.exception.ErrorMessage;
import com.kadirkaganyuksel.exception.MessageType;
import com.kadirkaganyuksel.service.IAddressService;

public class AddressServiceImpl implements IAddressService{

	public void test() {
		
		throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null));
	}
	
}
