package com.demoweb.service;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.SearchOptionDto;
import com.demoweb.mapper.BoardMapper;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {

	@Setter
	private BoardMapper boardMapper;
	
	@Setter
	private TransactionTemplate transactionTemplate;
	
//	@Override
//	public void writeBoard(BoardDto board) {
//		// board.getBoardNo() : 아직 미정 - 0
//		// boardMapper.insertBoard(board); // board 테이블에 데이터 저장 -> boardNo 결정 (DB에서)
//		boardMapper.insertBoard2(board); // board 테이블에 데이터 저장 -> boardNo 결정 (DB에서)
//		// board.getBoardNo() : 새로 만든 글 번호
//		
//		for (BoardAttachDto attach : board.getAttachments()) {
//			attach.setBoardNo(board.getBoardNo()); // 위 게시글 insert 후 생성된 글번호 저장
//			boardMapper.insertBoardAttach(attach); // boardattach 테이블에 데이터 저장
//		}
//		
//	}
	
//	@Override
//	public void writeBoard(BoardDto board) {
//	
//		// transactionManager를 사용해서 트랜잭션을 시작하고 doIntransaction() 호출
//		// TransactionStatus.setRollbackOnly() 호출 -> rollback, 그렇지 않으면 자동 commit
//		transactionTemplate.execute(new TransactionCallback<Void>() {
//
//			@Override
//			public Void doInTransaction(TransactionStatus status) {
//
//				try {
//					boardMapper.insertBoard2(board);
//					int x = 10 / 0; // 트랜잭션 테스트를 위해서 강제 예외 발생
//					for (BoardAttachDto attach : board.getAttachments()) {
//						attach.setBoardNo(board.getBoardNo());
//						boardMapper.insertBoardAttach(attach);
//					}
//				} catch (Exception ex) {
//					System.out.println("글쓰기 실패");
//					status.setRollbackOnly();
//				} 
//				
//				return null;
//			}
//		});
//	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, 
				   propagation = Propagation.REQUIRED, 
				   isolation = Isolation.READ_COMMITTED)
	public void writeBoard(BoardDto board) {
	
		boardMapper.insertBoard2(board);
		
		// int x = 10 / 0; // 트랜잭션 테스트를 위해서 강제 예외 발생
		
		for (BoardAttachDto attach : board.getAttachments()) {
			attach.setBoardNo(board.getBoardNo());
			boardMapper.insertBoardAttach(attach);
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
	public int getBoardCountWithSearch(SearchOptionDto criteria) {
		return boardMapper.selectBoardCountWithSearch(criteria);
	}	
	
	@Override
	public List<BoardDto> findBaordByRangeWithSearch(SearchOptionDto criteria) {
		
		List<BoardDto> boards = boardMapper.selectBoardByRangeWithSearch(criteria);
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
		// BoardDto board = boardMapper.selectBoardByBoardNo3(boardNo);
		BoardDto board = boardMapper.selectBoardByBoardNo4(boardNo);
		
		return board;
		
	}

	@Override
	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		BoardAttachDto attach = boardMapper.selectBoardAttachByAttachNo(attachNo);
		return attach;
	}

	@Override
	public void deleteBoard(int boardNo) {
		boardMapper.updateBoardDeleted(boardNo);
		
	}

	@Override
	public void deleteBoardAttach(int attachNo) {
		
		boardMapper.deleteBoardAttach(attachNo);
		
	}

	@Override
	public void modifyBoard(BoardDto board) {
		
		boardMapper.updateBoard(board);
		
		if (board.getAttachments() != null) {
			for (BoardAttachDto attach : board.getAttachments()) {
				boardMapper.insertBoardAttach(attach);
			}
		}
		
	}

	@Override
	public void writeComment(BoardCommentDto comment) {
		
		boardMapper.insertComment(comment);
		
	}

	@Override
	public List<BoardCommentDto> findBoardCommentsByBoardNo(int boardNo) {
		List<BoardCommentDto> comments = boardMapper.selectBoardCommentsByBoardNo(boardNo);
		return comments;
	}

	@Override
	public void deleteComment(int commentNo) {
		
		boardMapper.updateCommentDeleted(commentNo);
		
	}

	@Override
	public void editComment(BoardCommentDto comment) {
		
		boardMapper.updateComment(comment);
		
	}

	@Override
	public void writeReComment(BoardCommentDto comment) {
		
		// 부모 댓글을 조회해서 자식 댓글(대댓글)의 step, depth를 설정
		BoardCommentDto parent = boardMapper.selectBoardCommentByCommentNo(comment.getCommentNo());
		comment.setGroupNo(parent.getGroupNo());
		comment.setStep(parent.getStep() + 1);
		comment.setDepth(parent.getDepth() + 1);
		
		// 새로 삽입될 대댓글보다 순서번호(step)가 뒤에 있는 대댓글의 step 수정 (+1)
		boardMapper.updateStep(parent);
		
		boardMapper.insertReComment(comment);
		
	}

}
















