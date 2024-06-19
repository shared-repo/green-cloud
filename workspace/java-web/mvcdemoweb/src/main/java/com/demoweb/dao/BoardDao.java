package com.demoweb.dao;

import java.util.ArrayList;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;

public interface BoardDao {

	void insertBoard(BoardDto board);

	ArrayList<BoardDto> selectAllBoard();

	ArrayList<BoardDto> selectBoardByRange(int start, int count);

	void insertBoardAttach(BoardAttachDto attach);

	// 글번호를 받아서 게시글 조회 및 반환 ( primary key 검색이므로 단일 객체 반환 )
	BoardDto selectBoardByBoardNo(int boardNo);

	ArrayList<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo);

	BoardAttachDto selectBoardAttachByAttachNo(int attachNo);

	void updateBoardDeleted(int boardNo);

	void deleteBoardAttach(int attachNo);

	void updateBoard(BoardDto board);

	void insertComment(BoardCommentDto comment);

	ArrayList<BoardCommentDto> selectBoardCommentByBoardNo(int boardNo);

	void deleteComment(int commentNo);

	void updateComment(BoardCommentDto comment);

	void insertReComment(BoardCommentDto comment);

	BoardCommentDto selectBoardCommentByCommentNo(int commentNo);

	void updateStep(BoardCommentDto parent);

	int selectBoardCount();

}