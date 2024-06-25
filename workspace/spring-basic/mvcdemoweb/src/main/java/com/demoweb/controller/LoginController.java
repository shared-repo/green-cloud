package com.demoweb.controller;

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Controller {
	
	@Override
	public ActionResult handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String method = req.getMethod().toLowerCase();
		if (method.equals("get")) {
			return doGet(req, resp);
		} else {
			return doPost(req, resp);
		}
	}
	
	public ActionResult doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		ActionResult ar = new ActionResult("account/login", false); 
		return ar;
	}
	
	public ActionResult doPost(HttpServletRequest req, HttpServletResponse resp) {
	
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		
		// 2. 요청 데이터 처리 
		// 2-1. 데이터베이스에서 데이터 조회
		
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		
		AccountService accountService = new AccountService();
		MemberDto loginMember = accountService.findMemberByMemeberIdAndPasswd(member);
		
		ActionResult ar = null;
		if (loginMember != null) { // 로그인 성공 ( 사용자가 입력한 id, password에 해당하는 데이터가 조회된 경우 )
			// 2-2. 로그인 처리 --> 세션에 데이터 저장
			HttpSession session = req.getSession(); // 서블릿에는 내장 객체 개념이 없기 때문에 request 객체로부터 session 객체 유도
			session.setAttribute("loginuser", loginMember);
			
			// 3-1. home으로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
			ar = new ActionResult("/mvcdemoweb/home", true);
		} else { // 로그인 실패 ( 사용자가 입력한 id, password에 해당하는 데이터가 없는 경우 )
			ar = new ActionResult("/mvcdemoweb/account/login?loginfail=true", true);
		}
		return ar;
	}

}
	
	
	
	
