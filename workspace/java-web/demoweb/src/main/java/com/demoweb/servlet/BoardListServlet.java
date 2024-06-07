package com.demoweb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;
import com.demoweb.ui.ThePager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/list" })
public class BoardListServlet extends HttpServlet {

	private BoardService boardService = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 0. page 관련 변수
		int page = 1;		// 현재 요청된 페이지 번호 (1 ~ )
		String sPageNo = req.getParameter("pageNo");
		try {
			page = Integer.parseInt(sPageNo);
		} catch (Exception ex) {
			//page = 1;
		}
		int pageSize = 3; 		// 한 페이지에 표시하는 데이터 갯수
		int pagerSize = 3;		// 한 번에 표시되는 페이지 번호 갯수
		int dataCount = boardService.getBoardCount();	// 전체 데이터 갯수
		String linkUrl = "list";// 페이지 번호를 클릭했을 때 요청을 보낼 URL ( 목록 페이지 경로 )
		
		int start = pageSize * (page - 1) + 1; // 현재 페이지의 첫번째 데이터 행 번호
		
		ThePager pager = new ThePager(dataCount, page, pageSize, pagerSize, linkUrl);
		
		// 1. 게시물 목록 데이터 조회
		ArrayList<BoardDto> boardList = boardService.findBaordByRange(start, pageSize);		
		
		// 2. JSP에서 읽을 수 있도록 request 객체에 데이터 저장
		req.setAttribute("boardList", boardList); // ArrayList<BoardDto> --> Object 형식으로 암시적 형변환
		req.setAttribute("pager", pager);
		
		// 3. JSP로 이동 (forward)		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		rd.forward(req, resp);
	}
	
}
