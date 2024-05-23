package com.demoweb.service;

import java.util.ArrayList;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

public class BoardService {

	private BoardDao boardDao = new BoardDao();
	
	public void writeBoard(BoardDto board) {
		
		boardDao.insertBoard(board); // board 테이블에 데이터 저장
		
		for (BoardAttachDto attach : board.getAttachments()) {
			boardDao.insertBoardAttach(attach); // boardattach 테이블에 데이터 저장
		}
		
	}
	
	public ArrayList<BoardDto> findAllBaord() {
		
		ArrayList<BoardDto> boards = boardDao.selectAllBoard();
		return boards;
		
	}
	
}
