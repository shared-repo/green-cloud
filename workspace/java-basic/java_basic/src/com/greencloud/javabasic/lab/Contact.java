package com.greencloud.javabasic.lab;

import java.io.Serializable;

public class Contact implements Serializable {
	private static int nextNo = 1;
	private int no;
	private String name;
	private String phone;
	private String email;

	public Contact() {
		no = nextNo;
		nextNo++;
	}

	public Contact(String name, String phone, String email) {
		this(); // 같은 클래스의 전달인자 없는 생성자 메서드 호출
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return String.format("[%3d][%10s][%13s][%s]", no, name, phone, email);
	}

}