package com.demoweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.dto.BoardCommentDto;
import com.demoweb.mapper.BoardMapper;
import com.demoweb.service.BoardService;

import lombok.Setter;

@RestController // 모든 @~Mapping에 @ResponseBody를 자동으로 설정 
@RequestMapping(path = { "/board" })
public class CommentRestController {
	
	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@PostMapping(path = { "/write-comment" }, produces = "text/plain;charset=utf-8")
	//@ResponseBody // @RestController 클래스에서는 생략
	public String writeComment(BoardCommentDto comment) {
		String result = "success";
		try {
			boardService.writeComment(comment);
		} catch (Exception ex) {
			result = "fail";
		}
		
		return result;
	}
	
	@GetMapping(path = { "/list-comment-as-json" }, produces = "application/json;charset=utf-8")
	public List<BoardCommentDto> listComment(int boardNo, Model model) {
		
		List<BoardCommentDto> comments = boardService.findBoardCommentsByBoardNo(boardNo);
		
		return comments;
	}
	
	@GetMapping(path = { "/delete-comment" })
	public String deleteComment(@RequestParam(required = false) Integer commentNo) {
		
		if (commentNo == null) {
			return "invalid comment no";
		}
		
		boardService.deleteComment(commentNo);
		
		return "success";
		
	}
	
	@PostMapping(path = { "/edit-comment" })
	public String editComment(BoardCommentDto comment) {
	
		boardService.editComment(comment);
		
		return "success";
		
	}
	
	@PostMapping(path = { "/write-recomment" })
	public String writeRecomment(BoardCommentDto comment) {
		
		boardService.writeReComment(comment);
		
		return "success";
	}

}
















