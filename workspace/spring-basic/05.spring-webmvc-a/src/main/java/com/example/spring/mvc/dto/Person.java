package com.example.spring.mvc.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Person {

	private String name;
	private String phone;
	private String email;
	private int age;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
}
