package com.demoweb.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberDto {
	
	@NotBlank(message = "아이디를 입력하세요")
	@Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "아이디는 8 ~ 20개의 영문자 숫자 조합이어야 합니다")
	private String memberId;
	@NotBlank(message = "패스워드를 입력하세요")
	@Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "패스워드는 8 ~ 20개의 영문자 숫자 조합이어야 합니다")
	private String passwd;
	@NotBlank(message = "이메일을 입력하세요")
	@Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", 
			 message = "이메일 형식 오류")
	private String email;
	
	private String userType;
	private Date regDate;
	private boolean active;

}
