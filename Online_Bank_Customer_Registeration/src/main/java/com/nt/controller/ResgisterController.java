package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.RequestDto;
import com.nt.dto.ResponseDto;
import com.nt.service.RegisterServiceImpl;
import com.nt.utility.Constants;
import com.nt.utility.ResponseMessage;

@RestController
@RequestMapping("/bank")
public class ResgisterController {

	private RegisterServiceImpl registerService;

	public ResgisterController(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}

	// just testing API working or not
	@GetMapping("/test")
	public ResponseEntity<String> TestingMethod() {
		return ResponseEntity.ok("Welcome To Online Bank!");
	}

	// This API for Bank Users Registration fills all details
	@PostMapping("/register")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestBody RequestDto requestDto) {

		if (requestDto.getFirstName() == null || requestDto.getFirstName().isBlank() || requestDto.getEmail() == null
				|| requestDto.getEmail().isBlank() || requestDto.getPassword() == null
				|| requestDto.getPassword().isBlank() || requestDto.getMobileNo() == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
							.message("you enter invalid details for Registration").status(Constants.FAILED)
							.object(requestDto).build());
		}
		ResponseDto saveUser = registerService.saveUser(requestDto);
		if (saveUser != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_CREATED)
							.message("Registeration Successfully!").status(Constants.SUCEESS).object(saveUser).build());
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
						.message("Registeration  failed").status(Constants.FAILED).object(saveUser).build());
	}

	@GetMapping("/userslist")
	public ResponseEntity<ResponseMessage> getAllUsers() {
		List<ResponseDto> showAllUsers = registerService.showAllUsers();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_ACCEPTED).status(Constants.SUCEESS)
						.message("All Bank Users List").list(showAllUsers).build());
	}

	@PostMapping("/accountgetbyid/{id}")
	public ResponseEntity<ResponseMessage> getOneUserById(@PathVariable int id) {

		if (id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
							.status(Constants.FAILED).message("Invalid Id Number").build());
		}

		ResponseDto bankUserById = registerService.getBankUserById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_ACCEPTED).status(Constants.SUCEESS)
						.message("Id Founded Successfully!").object(bankUserById).build());
	}

	@PostMapping("/deleteuser/id")
	public ResponseEntity<ResponseMessage> deleteBankUser(@RequestParam int id) {

		if (id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
							.status(Constants.FAILED).message("Invalid Id Number").build());
		}
		ResponseDto deleteBankUserById = registerService.deleteBankUserById(id);

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(ResponseMessage.builder().statusCode(HttpURLConnection.HTTP_ACCEPTED).status(Constants.SUCEESS)
						.message("User Deleted Successfull!").object(deleteBankUserById).build());
	}
	
	
	
	
	

}
