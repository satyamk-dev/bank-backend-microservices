package com.nt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto{
	private Integer bid;
	private String firstName;
	private String lastName;
	private Long mobileNo;
	private String email;
	private String address;
	private Long accountNo;
//	private String password;
	}



