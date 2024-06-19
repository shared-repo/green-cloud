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
import jakarta.servlet.http.HttpSession;

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
		
		// 2. 요청 데이터 처리 
		// 2-1. 데이터베이스에서 데이터 조회
		// System.out.printf("[%s][%s]\n", memberId, passwd);
		
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		
		AccountService accountService = new AccountService();
		// MemberDto loginMember = accountService.findMemberByMemeberIdAndPasswd(memberId, passwd);
		MemberDto loginMember = accountService.findMemberByMemeberIdAndPasswd(member);		
		// System.out.println(loginMember);
		
		if (loginMember != null) { // 로그인 성공 ( 사용자가 입력한 id, password에 해당하는 데이터가 조회된 경우 )
			// 2-2. 로그인 처리 --> 세션에 데이터 저장
			HttpSession session = req.getSession(); // 서블릿에는 내장 객체 개념이 없기 때문에 request 객체로부터 session 객체 유도
			session.setAttribute("loginuser", loginMember);
			
			// 3-1. home으로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
			resp.sendRedirect("/mvcdemoweb/home");
		} else { // 로그인 실패 ( 사용자가 입력한 id, password에 해당하는 데이터가 없는 경우 )
			
//			// JSP에서 사용할 수 있도록 request 객체에 데이터 저장
//			req.setAttribute("loginfail", true);
//			
//			// 3-2. login.jsp로 이동 ( forward로 이동 )
//			// doGet(req, resp);
//			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/account/login.jsp");
//			rd.forward(req, resp);
			
			// 실패 후 사용자가 F5(새로고침) 했을 때 다시 전송하는 것을 막기위해 redirect로 이동
			resp.sendRedirect("/mvcdemoweb/account/login?loginfail=true"); // URL rewriting : 직접 주소 뒤에 전송 데이터 구성
		}
		
				
		
		
	}
	
}























