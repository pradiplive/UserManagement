package com.crm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CRMApplication {

	public static void main(String[] args) {
		SpringApplication.run(CRMApplication.class, args);
	}
	
	@Bean 
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
