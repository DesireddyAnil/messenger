package com.protonmoney.messenger.exceptions;

public class DuplicateUsernameException extends Exception{
	public DuplicateUsernameException(String message){
		super(message);
	}
}
