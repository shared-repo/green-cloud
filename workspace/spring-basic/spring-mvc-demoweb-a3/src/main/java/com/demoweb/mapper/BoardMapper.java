package com.demoweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demoweb.dto.BoardDto;

// MyBatis Mapper.xml 파일과 연동해서 동작하는 DAO 객체 생성할 때 적용할 인터페이스
// Mapper.xml 파일과 동일하게 작성 (경로, 이름, 전달인자, 결과형, ... )
@Mapper // root-context.xml 파일의 <bean id="boardMapper" ... 을 대신하는 annotation
public interface BoardMapper {

	void insertBoard(BoardDto board);

	List<BoardDto> selectAllBoard();

	BoardDto selectBoardByBoardNo(int boardNo);
}
