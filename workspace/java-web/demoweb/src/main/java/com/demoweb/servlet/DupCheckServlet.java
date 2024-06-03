package com.demoweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/account/dup-check" })
public class DupCheckServlet extends HttpServlet {
	
	private AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("id");
		
		// 2. 요청 처리
		boolean dup = accountService.checkDuplication(memberId);
		
		// 3. 응답
		resp.setContentType("plain/text;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(dup);
	}
	
	
}























