package com.nt.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.dto.RequestDto;
import com.nt.dto.ResponseDto;
import com.nt.entity.RegisterUser;
import com.nt.exception.EmailExitException;
import com.nt.exception.IdNotFoundException;
import com.nt.exception.NoUsersListException;
import com.nt.repository.RegisterRepository;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private ModelMapper modelMapper;

	private RegisterRepository registerRepository;
	private Argon2PasswordEncoder argon2Password;

	public RegisterServiceImpl(RegisterRepository registerRepository, Argon2PasswordEncoder argon2Password) {
		this.registerRepository = registerRepository;
		this.argon2Password = argon2Password;
	}

//	@Autowired
//	public void setRepository(RegisterRepository registerRepository) {
//		this.registerRepository=registerRepository;
//	}

	@Override
	public ResponseDto saveUser(RequestDto requestDto) {

		if (registerRepository.existsByEmail(requestDto.getEmail())) {
			throw new EmailExitException("Email already exists!");
		}

		// this is for entity class for map to java class to Database
		RegisterUser registerUser = new RegisterUser();

		// BeanUtils are copy the all object data at a time source to destination
		BeanUtils.copyProperties(requestDto, registerUser);

		// here I take original password and encrypt this password
		String originalPassword = registerUser.getPassword();
		String encodePassword = argon2Password.encode(originalPassword);
		// here i set encrypt Password
		registerUser.setPassword(encodePassword);

		// here i call save method and send user details to save to database and also
		// get all data for returning
		RegisterUser userDetails = registerRepository.save(registerUser);

		// This is ResponseDto to taking returning userDetails
		ResponseDto responseDto = new ResponseDto();

		// here copy Object return data to ResponseDto
		BeanUtils.copyProperties(userDetails, responseDto);

		return responseDto;

	}

	@Override
	public List<ResponseDto> showAllUsers() {
		List<RegisterUser> all = registerRepository.findAll();

		if (all.isEmpty()) {
			throw new NoUsersListException("No Users List");
		}

		// List<ResponseDto> list = new ArrayList<>();

		// Copy one list to another list but not same Data type
//		for(RegisterUser register: all) {
//			ResponseDto response = new ResponseDto();
//			response.setBid(register.getBid());
//			response.setFirstName(register.getFirstName());
//			response.setLastName(register.getLastName());
//			response.setEmail(register.getEmail());
//			response.setMobileNo(register.getMobileNo());
//			list.add(response);
//		}

		// using for loop copy all list to list using BeanUtils.copyProperties
//		for(RegisterUser register : all) {
//			ResponseDto response = new ResponseDto();
//			BeanUtils.copyProperties(register, response);
//			list.add(response);
//		}

		// using Stream copy list to list
//		List<ResponseDto> list = all.stream().map(user -> {
//			ResponseDto response = new ResponseDto();
//			response.setBid(user.getBid());
//			response.setFirstName(user.getFirstName());
//			response.setLastName(user.getLastName());
//			response.setEmail(user.getEmail());
//			response.setMobileNo(user.getMobileNo());
//			return response;
//		}).toList();

		// using stream and BeanUtils.copyProperties
		return all.stream().map(ele -> {
			ResponseDto dto = new ResponseDto();
			BeanUtils.copyProperties(ele, dto);
			return dto;
		}).toList();
		// return all.stream().map(ele -> modelMapper.map(ele,
		// ResponseDto.class)).toList();
	}

	@Override
	public ResponseDto getBankUserById(Integer id) {

//		Optional<RegisterUser> userIdData = registerRepository.findById(id);
//
//		if (userIdData.isEmpty()) {
//			throw new IdNotFoundException("User ID not available");
//		}

		RegisterUser userIdData = registerRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("User ID not available"));

		ResponseDto dto = new ResponseDto();
		BeanUtils.copyProperties(userIdData, dto);
		return dto;
	}

	@Override
	public ResponseDto deleteBankUserById(Integer id) {

		RegisterUser userById = registerRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("User ID Not available"));

		registerRepository.deleteById(id);

		ResponseDto dto = new ResponseDto();
		BeanUtils.copyProperties(userById, dto);
		return dto;
	}

}
