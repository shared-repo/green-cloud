package com.demoweb.dto;

import java.util.Date;

import lombok.Data;

@Data // Getter, Setter, ToString, ... 자동 생성
public class MemberDto {
	
	private String memberId;
	private String passwd;
	private String email;
	private String userType;
	private Date regDate;
	private boolean active;

}
