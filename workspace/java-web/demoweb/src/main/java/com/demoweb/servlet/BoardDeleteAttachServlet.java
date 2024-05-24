package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/delete-attach" })
public class BoardDeleteAttachServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 데이터 읽기
		String sAttachNo = req.getParameter("attachno");
		int attachNo = Integer.parseInt(sAttachNo);
		String sBoardNo = req.getParameter("boardno");
		int boardNo = Integer.parseInt(sBoardNo);
		
		// 2. 데이터 삭제  
//		BoardService boardService = new BoardService();
//		boardService.deleteBoardAttach(attachNo);
		
		// 3. 편집화면으로 이동
		resp.sendRedirect("edit?boardno=" + boardNo);
	}
	
}







