package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/detail" })
public class BoardDetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 데이터 읽기
		String sBoardNo = req.getParameter("boardno"); // 요청 데이터 읽기 : 사용자가 선택한 글번호 읽기
		int boardNo = Integer.parseInt(sBoardNo);
				
		// 2. 요청 처리 : 데이터베이스에 데이터 저장 -> BoardService를 호출해서 처리
		BoardService boardService = new BoardService();				
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		// 2. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		req.setAttribute("board", board);
		
		// 3. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
	}

}