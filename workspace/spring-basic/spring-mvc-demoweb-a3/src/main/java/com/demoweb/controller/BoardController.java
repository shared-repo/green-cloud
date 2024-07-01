package com.demoweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;
import com.demoweb.service.BoardServiceImpl;

import lombok.Setter;

@Controller
@RequestMapping(path = { "/board" })
public class BoardController {
	
	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@GetMapping(path = {"/list"})
	public String list(Model model) {
		
		List<BoardDto> boards = boardService.findAllBaord();
		model.addAttribute("boardList", boards); // Model 타입 전달인자에 데이터 저장 -> View(JSP)로 전달
		
		return "board/list-without-page"; 	// /WEB-INF/views/ + board/list-without-page + .jsp
	}
	
	@GetMapping(path = { "/write" })
	public String writeForm() {
		
		return "board/write";
	}
	
	@PostMapping(path = { "/write" })
	public String write(@ModelAttribute("board") BoardDto board) {
		
		// 데이터 읽기 : 컨트롤러 메서드의 전달인자를 통해 자동으로 데이터 읽기 실행	
		// 데이터 처리 : 서비스 호출
		boardService.writeBoard(board);
		
		// 이동		
		return "redirect:list";
	}
	
	@GetMapping(path = { "/detail" })
	public String detailWithQueryString(@RequestParam(value="boardno", required = false) Integer boardNo, Model model) {
		
		if (boardNo == null) { // 요청 데이터의 값이 없는 경우
			return "redirect:list";
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	

}













