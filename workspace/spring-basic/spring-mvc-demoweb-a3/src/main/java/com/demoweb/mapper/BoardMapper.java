package com.demoweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;

// MyBatis Mapper.xml 파일과 연동해서 동작하는 DAO 객체 생성할 때 적용할 인터페이스
// Mapper.xml 파일과 동일하게 작성 (경로, 이름, 전달인자, 결과형, ... )
@Mapper // root-context.xml 파일의 <bean id="boardMapper" ... 을 대신하는 annotation
public interface BoardMapper {

	void insertBoard(BoardDto board);
	void insertBoard2(BoardDto board);
	void insertBoard3(BoardDto board);

	List<BoardDto> selectAllBoard();

	BoardDto selectBoardByBoardNo(int boardNo);
	BoardDto selectBoardByBoardNo2(int boardNo);
	BoardDto selectBoardByBoardNo3(int boardNo);
	BoardDto selectBoardByBoardNo4(int boardNo);

	void insertBoardAttach(BoardAttachDto attach);
	List<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo);
	BoardAttachDto selectBoardAttachByAttachNo(int attachNo);
	List<BoardDto> selectBoardByRange(@Param("from") int from, @Param("to") int to);
	int selectBoardCount();
	
	void insertComment(BoardCommentDto comment);
	List<BoardCommentDto> selectBoardCommentsByBoardNo(int boardNo);
	void updateCommentDeleted(int commentNo);
	void updateComment(BoardCommentDto comment);
	BoardCommentDto selectBoardCommentByCommentNo(int commentNo);
	void updateStep(BoardCommentDto parent);
	void insertReComment(BoardCommentDto comment);
}













