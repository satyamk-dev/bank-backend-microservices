package com.nt.errorcontroller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class MyErrorController implements ErrorController{
	
	
	@GetMapping("/error")
	public ResponseEntity<String> getMyErrorPage(){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something want wrong!");
	}
	
	

}
