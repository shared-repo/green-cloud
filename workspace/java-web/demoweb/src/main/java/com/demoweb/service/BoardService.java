package com.demoweb.service;

import java.util.ArrayList;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardDto;

public class BoardService {

	private BoardDao boardDao = new BoardDao();
	
	public void writeBoard(BoardDto board) {
		
		boardDao.insertBoard(board);
		
	}
	
	public ArrayList<BoardDto> findAllBaord() {
		
		return boardDao.selectAllBoard();
		
	}
	
}
