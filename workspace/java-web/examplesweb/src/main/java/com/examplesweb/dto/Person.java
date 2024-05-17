package com.examplesweb.dto;

import java.io.Serializable;

// 1. Serializable 인터페이스 구현
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 3. 기본 생성자 메서드 (전달인자 생성자 메서드)
	public Person() {}
	
	// 2. private member + public getter / setter
	private String name;
	private String email;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	

}
