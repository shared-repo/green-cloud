package com.demoweb.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardCommentDto {
	
	private int commentNo;
	private int boardNo;
	private String content;
	private String writer;
	private Date writeDate;
	private Date modifyDate;
	private boolean deleted;
	
	private int groupNo;
	private int step;
	private int depth;

}
