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
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/board/write" })
public class BoardWriteServlet extends HttpServlet {

	private BoardService boardService = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1-1. 로그인 여부 확인 : Session에 인증 데이터있는지 확인
		HttpSession session = req.getSession(); // 서블릿에는 session 내장 객체가 없으므로 request 객체에서 유도
		if (session.getAttribute("loginuser") == null) { // 1.2. 로그인 하지 않은 사용자는
			// 로그인 화면으로 이동 (redirect)
			resp.sendRedirect("/demoweb/account/login");
			return;
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		BoardDto board = new BoardDto();
		board.setTitle(req.getParameter("title"));
		board.setWriter("iamuserone");
		board.setContent(req.getParameter("content"));
		
		boardService.writeBoard(board);
		
		resp.sendRedirect("/demoweb/board/list");
		
		
	}
	
}
