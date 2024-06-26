package com.demoweb.dto;

import java.util.ArrayList;
import java.util.Date;

public class BoardDto {
	
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date writeDate;
	private Date modifyDate;
	private int readCount;
	private boolean deleted;
	
	// board 테이블과 boardattach 테이블 사이의 1 : Many 관계를 구현하는 필드
	private ArrayList<BoardAttachDto> attachments;
	
	// board 테이블과 boardcomment 테이블 사이의 1 : Many 관계를 구현하는 필드
	private ArrayList<BoardCommentDto> comments;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public ArrayList<BoardAttachDto> getAttachments() {
		return attachments;
	}
	public void setAttachments(ArrayList<BoardAttachDto> attachments) {
		this.attachments = attachments;
	}
	public ArrayList<BoardCommentDto> getComments() {
		return comments;
	}
	public void setComments(ArrayList<BoardCommentDto> comments) {
		this.comments = comments;
	}

}
