package com.nt.service;

import java.util.List;

import com.nt.dto.RequestDto;
import com.nt.dto.ResponseDto;

public interface RegisterService {

	//This abstract method for register user and save to Database
	public ResponseDto saveUser(RequestDto requestDto);
	
	//This abstract method to Getting All Register Users
	public List<ResponseDto> showAllUsers();
	
	//This abstract Method for getting user by id/
	public ResponseDto getBankUserById(Integer id);
	
	//This abstract method for deleting users by id
	public ResponseDto deleteBankUserById(Integer id);
	

	
	
	 
}
