package com.nt.utility;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ResponseMessage {
	
	private Integer statusCode;
	
	private String status;
	
	private String message;
	
	private Object object;
	
	private List<?> list;
	
	
	
}
