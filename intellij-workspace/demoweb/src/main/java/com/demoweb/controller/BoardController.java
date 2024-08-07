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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;
import com.demoweb.service.BoardServiceImpl;
import com.demoweb.ui.ThePager;
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
	
//	@GetMapping(path = {"/list"})
//	public String listAll(Model model) {
//		
//		List<BoardDto> boards = boardService.findAllBaord();
//		model.addAttribute("boardList", boards); // Model 타입 전달인자에 데이터 저장 -> View(JSP)로 전달
//		
//		return "board/list-without-page"; 	// /WEB-INF/views/ + board/list-without-page + .jsp
//	}
	@GetMapping(path = {"/list"})
	public String listRange(@RequestParam(name="pageNo", defaultValue = "1") int pageNo, 
							HttpServletRequest req, Model model) {
		
		int pageSize = 3; 		// 한 페이지에 표시하는 데이터 갯수
		int pagerSize = 3;		// 한 번에 표시되는 페이지 번호 갯수
		int dataCount = boardService.getBoardCount();	// 전체 데이터 갯수
		String uri = req.getRequestURI();
		String linkUrl = uri.substring(uri.lastIndexOf("/") + 1); // 페이지 번호를 클릭했을 때 요청을 보낼 URL ( 목록 페이지 경로 )
		String queryString = req.getQueryString();
		
		int start = pageSize * (pageNo - 1) + 1; // 현재 페이지의 첫번째 데이터 행 번호
		
		// ThePager pager = new ThePager(dataCount, page, pageSize, pagerSize, linkUrl);
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl, queryString);
		
		List<BoardDto> boards = boardService.findBaordByRange(start, pageSize);		

		model.addAttribute("boardList", boards); // Model 타입 전달인자에 데이터 저장 -> View(JSP)로 전달
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "board/list"; 	// /WEB-INF/views/ + board/list + .jsp
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		board.setAttachments(attachments);
		
		try {		
			boardService.writeBoard(board);
		} catch (Exception ex) {
			System.out.println("글쓰기 실패");
		}
		
		// 이동		
		return "redirect:list";
	}
	
	@GetMapping(path = { "/detail" }) // 주소?d1=v1&d2=v2
	public String detailWithQueryString(
			@RequestParam(value="boardno", required = false) Integer boardNo, 
			@RequestParam(value="pageNo", defaultValue = "1") Integer pageNo, Model model) {
		
		
		if (boardNo == null) { // 요청 데이터의 값이 없는 경우
			return "redirect:list";
		}
		
		// BoardDto board = boardService.findBoardByBoardNo(boardNo);
		BoardDto board = boardService.findBoardByBoardNo2(boardNo);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		
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
	
	@GetMapping(path = { "/delete" })
	public String delete(int boardNo, @RequestParam(defaultValue = "-1") int pageNo) {
		
		if (pageNo == -1) {
			return "redirect:list";
		}
		
		boardService.deleteBoard(boardNo);
		
		return String.format("redirect:list?pageNo=%d", pageNo);
		
	}
	@GetMapping(path = { "/edit" })
	public String showEditForm(int boardNo, @RequestParam(defaultValue = "-1") int pageNo, Model model) {
		
		if (pageNo == -1) {
			return "redirect:list";
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		
		return "board/edit";
	}
	
	@PostMapping(path = { "/edit" })
	public String editBoard(BoardDto board, MultipartFile attach, HttpServletRequest req, 
							@RequestParam(required = false) Integer pageNo) {
		
		if (board.getBoardNo() == 0 || pageNo == null) {
			return "redirect:list";
		}
		
		if (attach != null && attach.getSize() > 0) {
			BoardAttachDto attachment = new BoardAttachDto();
			ArrayList<BoardAttachDto> attachments = new ArrayList<>();
			try {
				String dir = req.getServletContext().getRealPath("/board-attachments");
				String userFileName = attach.getOriginalFilename();
				String savedFileName = Util.makeUniqueFileName(userFileName);			
				attach.transferTo(new File(dir, savedFileName)); // 파일 저장
				
				attachment.setBoardNo(board.getBoardNo());
				attachment.setUserFileName(userFileName);
				attachment.setSavedFileName(savedFileName);
				attachments.add(attachment);			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			board.setAttachments(attachments);
		}
		
		try {		
			boardService.modifyBoard(board);
		} catch (Exception ex) {
			System.out.println("글수정 실패");
			return String.format("redirect:edit?boardNo=%d&pageNo=%d", board.getBoardNo(), pageNo);
		}
		
		return String.format("redirect:detail?boardno=%d&pageNo=%d", board.getBoardNo(), pageNo);
	}
	
	@GetMapping(path = { "/delete-attach" })
	@ResponseBody
	public String deleteAttach(@RequestParam(required = false) Integer attachNo, HttpServletRequest req) {
		
		if (attachNo == null) {
			return "Invalid attachNo";
		}		
		BoardAttachDto attach = boardService.findBoardAttachByAttachNo(attachNo);
		String dirPath = req.getServletContext().getRealPath("/board-attachments");
		File file = new File(dirPath, attach.getSavedFileName());
		if (file.exists()) {
			file.delete();
		}
		boardService.deleteBoardAttach(attachNo);
		
		return "success";
	}
	
	
	@GetMapping(path = { "/list-comment" })
	public String listComment(int boardNo, Model model) {
		
		List<BoardCommentDto> comments = boardService.findBoardCommentsByBoardNo(boardNo);
		model.addAttribute("comments", comments);
		
		return "board/comment-list";
	}
	

}













