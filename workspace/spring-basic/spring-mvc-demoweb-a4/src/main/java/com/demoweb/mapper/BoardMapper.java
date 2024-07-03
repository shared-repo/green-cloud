package com.demoweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

// MyBatis Mapper.xml 파일과 연동해서 동작하는 DAO 객체 생성할 때 적용할 인터페이스
// Mapper.xml 파일과 동일하게 작성 (경로, 이름, 전달인자, 결과형, ... )
@Mapper // root-context.xml 파일의 <bean id="boardMapper" ... 을 대신하는 annotation
public interface BoardMapper {

	@Insert("INSERT INTO board (boardno, title, writer, content) " +
			"VALUES (BOARD_SEQUENCE.NEXTVAL, #{ title }, #{ writer }, #{ content })")
	@Options(useGeneratedKeys = true, keyColumn = "boardNo", keyProperty = "boardNo")
	void insertBoard(BoardDto board);
	@Insert("INSERT INTO board (boardno, title, writer, content) " +
			"VALUES (BOARD_SEQUENCE.NEXTVAL, #{ title }, #{ writer }, #{ content })")
	@SelectKey(statement = "SELECT BOARD_SEQUENCE.CURRVAL FROM DUAL",
			   resultType=Integer.class, keyProperty = "boardNo", before = false)
	void insertBoard2(BoardDto board);
	@Insert("INSERT INTO board (boardno, title, writer, content) " +
			"VALUES (#{ boardNo }, #{ title }, #{ writer }, #{ content })")
	@SelectKey(statement = "SELECT BOARD_SEQUENCE.NEXTVAL FROM DUAL",
			   resultType=Integer.class, keyProperty = "boardNo", before = true)
	void insertBoard3(BoardDto board);

	@Select("SELECT boardNo, title, writer, readCount, writeDate, modifyDate, deleted " +
			"FROM board " +
			"ORDER BY boardno DESC ")
	List<BoardDto> selectAllBoard();
	@Select(  "<![CDATA[ "
			+ "		SELECT * "
			+ "		FROM "
			+ "		("
			+ "			SELECT ROWNUM idx, a.* "
			+ "			FROM "
			+ "			("
			+ "				SELECT boardno, title, writer, readcount, writedate, modifydate, deleted "
			+ "				FROM board "
			+ "				ORDER BY boardno DESC "
			+ "			) a "
			+ "			WHERE ROWNUM < #{ to } "
			+ "		) b "
			+ "		WHERE b.idx >= #{ from } "
			+ "]]>")
	List<BoardDto> selectBoardByRange(@Param("from") int from, @Param("to") int to);

	BoardDto selectBoardByBoardNo(int boardNo);
	BoardDto selectBoardByBoardNo2(int boardNo);
	BoardDto selectBoardByBoardNo3(int boardNo);

	void insertBoardAttach(BoardAttachDto attach);
	List<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo);
	BoardAttachDto selectBoardAttachByAttachNo(int attachNo);
	int selectBoardCount();
}
