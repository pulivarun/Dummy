package com.capgemini.tcc.exception;

public class TakeCareClinicException extends Exception {
	
	String message;
	
	public TakeCareClinicException(String msg)
	{
		message=msg;
	}
	
	@Override
	public String getMessage()
	{
		return message;
	}

}
