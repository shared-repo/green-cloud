package com.demoweb.service;

import java.util.List;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.mapper.BoardMapper;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {

	@Setter
	private BoardMapper boardMapper;
	
	@Override
	public void writeBoard(BoardDto board) {
		// board.getBoardNo() : 아직 미정 - 0
		// boardMapper.insertBoard(board); // board 테이블에 데이터 저장 -> boardNo 결정 (DB에서)
		boardMapper.insertBoard2(board); // board 테이블에 데이터 저장 -> boardNo 결정 (DB에서)
		// board.getBoardNo() : 새로 만든 글 번호
		
		for (BoardAttachDto attach : board.getAttachments()) {
			attach.setBoardNo(board.getBoardNo()); // 위 게시글 insert 후 생성된 글번호 저장
			boardMapper.insertBoardAttach(attach); // boardattach 테이블에 데이터 저장
		}
		
	}
	
	@Override
	public List<BoardDto> findAllBaord() {		
		List<BoardDto> boards = boardMapper.selectAllBoard();
		return boards;
	}
	

	@Override
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}	
	
	@Override
	public List<BoardDto> findBaordByRange(int start, int count) {
		
		List<BoardDto> boards = boardMapper.selectBoardByRange(start, start + count);
		return boards;
		
	}
	
	@Override
	public BoardDto findBoardByBoardNo(int boardNo) {
		
		// 게시글 조회
		BoardDto board = boardMapper.selectBoardByBoardNo(boardNo);
		
		// 첨부파일 조회
		List<BoardAttachDto> attaches = boardMapper.selectBoardAttachByBoardNo(boardNo);		
		board.setAttachments(attaches);
		
//		// 댓글 조회
//		ArrayList<BoardCommentDto> comments = boardDao.selectBoardCommentByBoardNo(boardNo);
//		board.setComments(comments);
		
		return board;
		
	}
	@Override
	public BoardDto findBoardByBoardNo2(int boardNo) {
		
		// 게시글 조회
		// BoardDto board = boardMapper.selectBoardByBoardNo2(boardNo);
		BoardDto board = boardMapper.selectBoardByBoardNo3(boardNo);
		
		return board;
		
	}

	@Override
	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		BoardAttachDto attach = boardMapper.selectBoardAttachByAttachNo(attachNo);
		return attach;
	}

//	@Override
//	public void deleteBoard(int boardNo) {
//		boardDao.updateBoardDeleted(boardNo);
//		
//	}
//
//	@Override
//	public void deleteBoardAttach(int attachNo) {
//		
//		boardDao.deleteBoardAttach(attachNo);
//		
//	}
//
//	@Override
//	public void modifyBoard(BoardDto board) {
//		
//		boardDao.updateBoard(board);
//		
//		for (BoardAttachDto attach : board.getAttachments()) {
//			boardDao.insertBoardAttach(attach);
//		}
//		
//	}
//
//	@Override
//	public void writeComment(BoardCommentDto comment) {
//		
//		boardDao.insertComment(comment);
//		
//	}
//
//	@Override
//	public void deleteComment(int commentNo) {
//		
//		boardDao.deleteComment(commentNo); // boardDao.updateCommentDeleted(commentNo)
//		
//	}
//
//	@Override
//	public void editComment(BoardCommentDto comment) {
//		
//		boardDao.updateComment(comment);
//		
//	}
//
//	@Override
//	public void writeReComment(BoardCommentDto comment) {
//		
//		// 부모 댓글을 조회해서 자식 댓글(대댓글)의 step, depth를 설정
//		BoardCommentDto parent = boardDao.selectBoardCommentByCommentNo(comment.getCommentNo());
//		comment.setGroupNo(parent.getGroupNo());
//		comment.setStep(parent.getStep() + 1);
//		comment.setDepth(parent.getDepth() + 1);
//		
//		// 새로 삽입될 대댓글보다 순서번호(step)가 뒤에 있는 대댓글의 step 수정 (+1)
//		boardDao.updateStep(parent);
//		
//		boardDao.insertReComment(comment);
//		
//	}

}
















