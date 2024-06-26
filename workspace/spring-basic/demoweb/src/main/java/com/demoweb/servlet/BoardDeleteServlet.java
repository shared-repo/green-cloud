package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/delete" })
public class BoardDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 데이터 읽기
		String sBoardNo = req.getParameter("boardno");
		int boardNo = Integer.parseInt(sBoardNo);
		int page = 1;		// 현재 요청된 페이지 번호 (1 ~ )
		String sPageNo = req.getParameter("pageNo");
		try {
			page = Integer.parseInt(sPageNo);
		} catch (Exception ex) {
			//page = 1;
		}
		
		// 2. 데이터 조회 ( 파일이름이 필요해서 수행 ) 
		BoardService boardService = new BoardService();
		boardService.deleteBoard(boardNo);
		
		resp.sendRedirect(String.format("list?pageNo=%d", page));
		//resp.sendRedirect("/demoweb/board/list");
	}
	
}







