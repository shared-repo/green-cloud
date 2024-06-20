package com.demoweb.controller;

import com.demoweb.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DupCheckController implements Controller {

	@Override
	public ActionResult handleRequest(HttpServletRequest req, HttpServletResponse resp) {

		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("id");
		
		// 2. 요청 처리
		AccountService accountService = new AccountService();
		boolean dup = accountService.checkDuplication(memberId);
		
		// 3. 응답
		resp.setContentType("plain/text;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(dup);
	}

}
