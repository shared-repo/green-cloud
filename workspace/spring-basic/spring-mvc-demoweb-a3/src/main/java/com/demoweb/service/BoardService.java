package com.demoweb.service;

import java.util.ArrayList;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;

public interface BoardService {

	void writeBoard(BoardDto board);

//	ArrayList<BoardDto> findAllBaord();
//
//	ArrayList<BoardDto> findBaordByRange(int start, int count);
//
//	BoardDto findBoardByBoardNo(int boardNo);
//
//	BoardAttachDto findBoardAttachByAttachNo(int attachNo);
//
//	void deleteBoard(int boardNo);
//
//	void deleteBoardAttach(int attachNo);
//
//	void modifyBoard(BoardDto board);
//
//	void writeComment(BoardCommentDto comment);
//
//	void deleteComment(int commentNo);
//
//	void editComment(BoardCommentDto comment);
//
//	void writeReComment(BoardCommentDto comment);
//
//	int getBoardCount();

}