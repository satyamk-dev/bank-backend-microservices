package com.nt.exception;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleException {
	
	@ExceptionHandler(MyException.class)
	public ResponseEntity<?> idNotFoundException(MyException ex){
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<?> allException(Exception ex){
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(ex.getMessage());
		
	}
	
	@ExceptionHandler(UserRegisterException.class) 
	public ResponseEntity<?> userRegisterExcetion(UserRegisterException ure){
		return ResponseEntity.badRequest().body(ure.getMessage());
	}
	
	@ExceptionHandler(EmailExitException.class) 
	public ResponseEntity<?> userRegisterExcetion(EmailExitException email){
		return ResponseEntity.badRequest().body(email.getMessage());
	}
	
	@ExceptionHandler(NoUsersListException.class)
	public ResponseEntity<?> noUserlistException(NoUsersListException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	
	

}
