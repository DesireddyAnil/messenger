package com.protonmoney.messenger.exceptions;

public class RequestFailedException extends Exception{
	public RequestFailedException(String message){
		super(message);
	}
}
