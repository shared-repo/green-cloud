package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.controller.HomeController;
import com.demoweb.controller.LoginController;
import com.demoweb.controller.RegisterController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home", "/account/*", "/board/*" })
public class DemoWebFrontController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 분석
		String url = req.getRequestURI();		
		String action = url.replace("/mvcdemoweb", ""); 	// /mvcdemoweb/request-path -> /request-path
		if (url.startsWith("/")) {
			action = action.substring(1);					// /request-path -> request-path
		}
		// System.out.println(action);
		
		// 2. Controller 선택 및 호출
		// 2-1. 요청 데이터 읽기 	( 요청 처리 Controller 호출 )
		// 2-2. 요청 처리			( 요청 처리 Controller 호출 )		
		String view = null;
		switch (action) {
		case "home": 
			HomeController controller1 = new HomeController();
			view = controller1.handleRequest(req);
			break;
		case "account/login": 
			LoginController controller2 = new LoginController();
			view = controller2.handleRequest(req);
			break;
		case "account/register": 
			RegisterController controller3 = new RegisterController();
			view = controller3.handleRequest(req);
			break;
		case "account/logout": break;
		}
		
		// 3. 뷰 선택 및 호출 ( JSP로 forward or 다른 Controller로 redirect )
		// 3-1. 응답 컨텐츠 생산 	
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}












