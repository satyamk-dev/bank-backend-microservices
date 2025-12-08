package com.nt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.modelmapper.ModelMapper;
@Configuration
public class BeanFactory {

	@Bean
	public Argon2PasswordEncoder passwordEncode() {
		return new Argon2PasswordEncoder(16, 32, 1,1<<12, 3);
	} 
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
