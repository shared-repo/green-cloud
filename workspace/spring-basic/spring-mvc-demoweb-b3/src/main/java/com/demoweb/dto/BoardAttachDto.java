package com.demoweb.dto;

import lombok.Data;

@Data
public class BoardAttachDto {
	
	private int attachNo;
	private int boardNo;
	private String userFileName;	// 사용자가 업로드한 파일 이름
	private String savedFileName;	// 서버에 저장한 파일 이름, 고유한 이름
	private int downloadCount;

}
