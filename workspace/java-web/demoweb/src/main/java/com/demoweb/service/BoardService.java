package com.demoweb.service;

import java.util.ArrayList;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

public class BoardService {

	private BoardDao boardDao = new BoardDao();
	
	public void writeBoard(BoardDto board) {
		// board.getBoardNo() : 아직 미정 - 0
		boardDao.insertBoard(board); // board 테이블에 데이터 저장 -> boardNo 결정 (DB에서)
		// board.getBoardNo() : 새로 만든 글 번호
		
		for (BoardAttachDto attach : board.getAttachments()) {
			attach.setBoardNo(board.getBoardNo()); // 위 게시글 insert 후 생성된 글번호 저장
			boardDao.insertBoardAttach(attach); // boardattach 테이블에 데이터 저장
		}
		
	}
	
	public ArrayList<BoardDto> findAllBaord() {
		
		ArrayList<BoardDto> boards = boardDao.selectAllBoard();
		return boards;
		
	}
	
}
