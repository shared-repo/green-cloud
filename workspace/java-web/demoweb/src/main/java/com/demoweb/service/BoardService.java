package com.demoweb.service;

import java.util.ArrayList;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
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
	
	public BoardDto findBoardByBoardNo(int boardNo) {
		
		// 게시글 조회
		BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
		
		// 첨부파일 조회
		ArrayList<BoardAttachDto> attaches = boardDao.selectBoardAttachByBoardNo(boardNo);		
		board.setAttachments(attaches);
		
		// 댓글 조회
		ArrayList<BoardCommentDto> comments = boardDao.selectBoardCommentByBoardNo(boardNo);
		board.setComments(comments);
		
		return board;
		
	}

	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		BoardAttachDto attach = boardDao.selectBoardAttachByAttachNo(attachNo);
		return attach;
	}

	public void deleteBoard(int boardNo) {
		boardDao.updateBoardDeleted(boardNo);
		
	}

	public void deleteBoardAttach(int attachNo) {
		
		boardDao.deleteBoardAttach(attachNo);
		
	}

	public void modifyBoard(BoardDto board) {
		
		boardDao.updateBoard(board);
		
		for (BoardAttachDto attach : board.getAttachments()) {
			boardDao.insertBoardAttach(attach);
		}
		
	}

	public void writeComment(BoardCommentDto comment) {
		
		boardDao.insertComment(comment);
		
	}

	public void deleteComment(int commentNo) {
		
		boardDao.deleteComment(commentNo); // boardDao.updateCommentDeleted(commentNo)
		
	}

	public void editComment(BoardCommentDto comment) {
		
		boardDao.updateComment(comment);
		
	}

	public void writeReComment(BoardCommentDto comment) {
		
		// 부모 댓글을 조회해서 자식 댓글(대댓글)의 step, depth를 설정
		BoardCommentDto parent = boardDao.selectBoardCommentByCommentNo(comment.getCommentNo());
		comment.setGroupNo(parent.getGroupNo());
		comment.setStep(parent.getStep() + 1);
		comment.setDepth(parent.getDepth() + 1);
		
		// 새로 삽입될 대댓글보다 순서번호(step)가 뒤에 있는 대댓글의 step 수정 (+1)
		boardDao.updateStep(parent);
		
		boardDao.insertReComment(comment);
		
	}
	
}
















