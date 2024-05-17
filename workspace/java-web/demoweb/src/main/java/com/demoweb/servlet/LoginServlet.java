package com.demoweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/account/login" })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/account/login.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		
		// 2. 요청 데이터 처리 ( 데이터베이스에서 데이터 조회 )
		// System.out.printf("[%s][%s]\n", memberId, passwd);
		
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		
		AccountService accountService = new AccountService();
		// MemberDto loginMember = accountService.findMembeByMemeberIdAndPasswd(memberId, passwd);
		MemberDto loginMember = accountService.findMembeByMemeberIdAndPasswd(member);
		
		System.out.println(loginMember);
		
		
		// 3. home으로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
		//    jsp로 이동 ( forward로 이동 )
		resp.sendRedirect("/demoweb/home");
		
	}
	
}























