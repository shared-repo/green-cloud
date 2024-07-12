package com.demoweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		List<BoardAttachEntity> attachments = new ArrayList<>();
		for (BoardAttachDto attach : board.getAttachments()) {
			attachments.add(attach.toEntity());
		}
		boardEntity.setAttachments(attachments);
		boardRepository.save(boardEntity); // save -> insert or update

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

		Optional<BoardEntity> entity = boardRepository.findById(boardNo);
		if (entity.isPresent()) {
			BoardEntity boardEntity = entity.get();
			BoardDto board = BoardDto.of(boardEntity);
//			List<BoardAttachDto> attachments = new ArrayList<>();
//			for (BoardAttachEntity entity2 : boardEntity.getAttachments()) {
//				attachments.add(BoardAttachDto.of(entity2));
//			}
			List<BoardAttachDto> attachments =
					boardEntity.getAttachments().stream()
							.map(BoardAttachDto::of)
							.toList();
			board.setAttachments(attachments);
			return board;
		} else {
			return null;
		}
		//return entity.isPresent() ? BoardDto.of(entity.get()) : null;
		
	}

	@Override
	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		 // Optional<BoardAttachEntity> entity = boardAttachRepository.findById(attachNo);
		// if return entity.isPresent() ? BoardAttachDto.of(entity.get()) : null;
		BoardAttachEntity entity = boardRepository.findBoardAttachByAttachNo(attachNo);
		return BoardAttachDto.of(entity);
	}

	@Override
	public void deleteBoard(int boardNo) {
		BoardEntity entity = boardRepository.findById(boardNo).get();
//		boardRepository.delete(entity); // 실제 데이터 삭제
		entity.setDeleted(true);
		boardRepository.save(entity);
		
	}

	@Override
	public void deleteBoardAttach(int attachNo) {

		// boardAttachRepository.deleteById(attachNo);	// 1
//		BoardAttachEntity entity = boardRepository.findBoardAttachByAttachNo(attachNo);
//		boardAttachRepository.delete(entity); // 2
		boardRepository.deleteBoardAttachByAttachNo(attachNo); // 3
	}

	@Override
	public void modifyBoard(BoardDto board) {

		BoardEntity entity = boardRepository.findById(board.getBoardNo()).get();
		entity.setTitle(board.getTitle());
		entity.setContent(board.getContent());
		boardRepository.save(entity);

		if (board.getAttachments() != null) {
			for (BoardAttachDto attach : board.getAttachments()) {
				boardAttachRepository.insertBoardAttach(attach.getBoardNo(),
						attach.getUserFileName(), attach.getSavedFileName());
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
















