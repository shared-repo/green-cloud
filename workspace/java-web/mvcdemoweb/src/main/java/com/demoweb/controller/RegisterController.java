package com.demoweb.controller;

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterController implements Controller {
	
	@Override
	public ActionResult handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		String method = req.getMethod().toLowerCase();
		if (method.equals("get")) {
			return doGet(req, resp);
		} else {
			return doPost(req, resp);
		}
	}
	
	
	private ActionResult doGet(HttpServletRequest req, HttpServletResponse resp) {
		ActionResult ar = new ActionResult("account/register", false); 
		return ar;
	}
		
	private ActionResult doPost(HttpServletRequest req, HttpServletResponse resp) {
	
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		String confirm = req.getParameter("confirm");
		String email = req.getParameter("email");
		
		// 2. 요청 데이터 처리 ( 데이터베이스에 데이터 저장 )		
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		member.setEmail(email);
		
		AccountService accountService = new AccountService();
		accountService.registerMember(member);		
		
		// 3. login으로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
		ActionResult ar = new ActionResult("login", true); 
		return ar;
		
	}

}







