package com.nt.exception;

public class EmailExitException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailExitException(String msg) {
		super(msg);
	}

}
