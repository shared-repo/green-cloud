package com.demoweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController implements Controller {
	
	@Override
	public ActionResult handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		// 요청 데이터 읽기
		// 요청 처리
		
		ActionResult ar = new ActionResult("account/login", false); 
		return ar;
	}

}
