package com.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.dto.BoardCommentDto;
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

}
