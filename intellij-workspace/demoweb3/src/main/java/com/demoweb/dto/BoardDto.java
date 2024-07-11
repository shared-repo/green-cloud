package com.demoweb.dto;

import java.util.Date;
import java.util.List;

import com.demoweb.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	private List<BoardAttachDto> attachments;
	
	// board 테이블과 boardcomment 테이블 사이의 1 : Many 관계를 구현하는 필드
	private List<BoardCommentDto> comments;

	public BoardEntity toEntity() {
		BoardEntity boardEntity = BoardEntity.builder()	.title(title)
													   	.writer(writer)
													   	.content(content)
//													   	.writeDate(writeDate)
//														.modifyDate(modifyDate)
														.readCount(readCount)
														.deleted(deleted)
														.build();
		return boardEntity;
	}
	public static BoardDto of(BoardEntity entity) {
		BoardDto board = BoardDto.builder()	.boardNo(entity.getBoardNo())
											.title(entity.getTitle())
											.writer(entity.getWriter())
											.content(entity.getContent())
											.readCount(entity.getReadCount())
											.writeDate(entity.getWriteDate())
											.modifyDate(entity.getModifyDate())
											.deleted(entity.isDeleted())
											.build();
		return board;
	}

}
