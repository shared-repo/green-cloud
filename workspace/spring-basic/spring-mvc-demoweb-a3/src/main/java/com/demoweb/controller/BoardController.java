package com.demoweb.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;
import com.demoweb.service.BoardServiceImpl;
import com.demoweb.view.DownloadView1;
import com.demoweb.view.DownloadView2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
	public String write(@ModelAttribute("board") BoardDto board, 
						MultipartFile attach, // <input type="file"의 데이터를 담은 변수 
						HttpServletRequest req) {
		
		// 데이터 읽기 : 컨트롤러 메서드의 전달인자를 통해 자동으로 데이터 읽기 실행	
		// 데이터 처리 : 서비스 호출
		BoardAttachDto attachment = new BoardAttachDto();
		ArrayList<BoardAttachDto> attachments = new ArrayList<>();
		try {
			String dir = req.getServletContext().getRealPath("/board-attachments");
			String userFileName = attach.getOriginalFilename();
			String savedFileName = Util.makeUniqueFileName(userFileName);			
			attach.transferTo(new File(dir, savedFileName)); // 파일 저장
			
			attachment.setUserFileName(userFileName);
			attachment.setSavedFileName(savedFileName);
			attachments.add(attachment);
			board.setAttachments(attachments);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		boardService.writeBoard(board);
		
		// 이동		
		return "redirect:list";
	}
	
	@GetMapping(path = { "/detail" }) // 주소?d1=v1&d2=v2
	public String detailWithQueryString(@RequestParam(value="boardno", required = false) Integer boardNo, Model model) {
		
		
		
		if (boardNo == null) { // 요청 데이터의 값이 없는 경우
			return "redirect:list";
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	@GetMapping(path = { "/detail/{boardNo}" }) // 주소/data
	public String detailWithPathVariable(@PathVariable("boardNo") Integer boardNo, Model model) {
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	
	@GetMapping(path = { "/download" })
	public View downloadWithQueryString(@RequestParam("attachno") int attachNo, Model model) {
		
		BoardAttachDto boardAttach = boardService.findBoardAttachByAttachNo(attachNo);
		
		model.addAttribute("attach", boardAttach); // View에서 사용하도록 데이터 전달
		
		// 다운로드 처리 -> 사용자 정의 View 사용
		return new DownloadView1();
		// return new DownloadView2();
	}	
	

}













