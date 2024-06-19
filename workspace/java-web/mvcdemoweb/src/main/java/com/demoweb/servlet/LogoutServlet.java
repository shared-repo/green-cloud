package com.demoweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/account/logout" })
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그아웃 처리 -> 세션에서 데이터 제거
		HttpSession session = req.getSession(); // 서블릿에서는 session이 내장 객체가 아니므로 request 객체로부터 유도
		// session.removeAttribute("loginuser"); // 세션에서 loginuser로 저장된 데이터 제거
		session.invalidate(); // 세션 전체 파괴 (모든 데이터 제거 + 세션 제거 : 초기화)
		
		// home으로 이동 (서블릿으로 이동 -> redirect)
		resp.sendRedirect("/mvcdemoweb/home");
	}
	
}














