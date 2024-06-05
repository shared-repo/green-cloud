package com.demoweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;
import com.demoweb.service.BoardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/edit-comment" })
public class CommentEditServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		String sBoardNo = req.getParameter("boardno");
		String sCommentNo = req.getParameter("commentno");
		int commentNo = Integer.parseInt(sCommentNo);
		String content = req.getParameter("content");
		
		BoardCommentDto comment = new BoardCommentDto();
		comment.setCommentNo(commentNo);
		comment.setContent(content);
		
		// 2. 요청 데이터 처리 ( 데이터베이스에 데이터 저장 )
		BoardService boardService = new BoardService();
		boardService.editComment(comment);		
		
		// 3. detail로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
		resp.sendRedirect(String.format("detail?boardno=%s", sBoardNo));
		
	}
	
}























