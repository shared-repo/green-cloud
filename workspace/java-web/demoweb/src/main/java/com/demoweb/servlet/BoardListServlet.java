package com.demoweb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;

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
		
		// 1. 게시물 목록 데이터 조회
		ArrayList<BoardDto> boardList = boardService.findAllBaord();
		
		// 2. JSP에서 읽을 수 있도록 데이터 저장
		req.setAttribute("boardList", boardList);
		
		// 3. JSP로 이동 (forward)		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		rd.forward(req, resp);
	}
	
}
