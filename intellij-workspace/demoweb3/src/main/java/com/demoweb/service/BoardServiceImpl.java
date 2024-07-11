package com.demoweb.service;

import java.util.ArrayList;
import java.util.List;

import com.demoweb.entity.BoardAttachEntity;
import com.demoweb.entity.BoardEntity;
import com.demoweb.repository.BoardAttachRepository;
import com.demoweb.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.mapper.BoardMapper;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {

	@Setter
	private BoardMapper boardMapper;

	@Setter
	private BoardRepository boardRepository;
	@Setter
	private BoardAttachRepository boardAttachRepository;
	
	@Setter
	private TransactionTemplate transactionTemplate;

	@Override
//	@Transactional(rollbackFor = Exception.class,
//				   propagation = Propagation.REQUIRED,
//				   isolation = Isolation.READ_COMMITTED)
	public void writeBoard(BoardDto board) {

		BoardEntity boardEntity = board.toEntity();
		boardRepository.save(boardEntity); // save -> insert or update

		List<BoardAttachEntity> attachments = new ArrayList<>();
		for (BoardAttachDto attach : board.getAttachments()) {
			attach.setBoardNo(boardEntity.getBoardNo());
			attachments.add(attach.toEntity());
			boardAttachRepository.save(attach.toEntity());
		}
		//boardEntity.setAttachments(attachments);
		//boardRepository.save(boardEntity); // save -> insert or update

	}
	
	@Override
	public List<BoardDto> findAllBaord() {		
		List<BoardDto> boards = boardMapper.selectAllBoard();
		return boards;
	}
	

	@Override
	public int getBoardCount() {
		return (int)boardRepository.count();
	}	
	
	@Override
	public List<BoardDto> findBaordByRange(int start, int count) {

		List<BoardDto> boards = boardMapper.selectBoardByRange(start, start + count);
		return boards;
		
	}
	@Override
	public List<BoardDto> findBaordByRange2(int pageNo, int count) {
		Pageable pageable = PageRequest.of(pageNo, count, Sort.by(Sort.Direction.DESC, "boardNo"));
		Page<BoardEntity> page = boardRepository.findAll(pageable);
		List<BoardDto> boards = new ArrayList<>();
		for (BoardEntity boardEntity : page.getContent()) {
			boards.add(BoardDto.of(boardEntity));
		}
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
















