package com.demoweb.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/account/register" })
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/account/register.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		String confirm = req.getParameter("confirm");
		String email = req.getParameter("email");
		// 2. 요청 데이터 처리
		System.out.printf("[%s][%s][%s][%s]\n", memberId, passwd, confirm, email);
		// 3. login으로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
		//    jsp로 이동 ( forward로 이동 )
		resp.sendRedirect("/demoweb/account/login");
		
	}
	
}























