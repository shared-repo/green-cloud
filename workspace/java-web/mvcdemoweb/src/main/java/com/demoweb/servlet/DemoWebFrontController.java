package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.controller.ActionResult;
import com.demoweb.controller.Controller;
import com.demoweb.controller.HomeController;
import com.demoweb.controller.LoginController;
import com.demoweb.controller.RegisterController;
import com.demoweb.factory.DemoWebControllerFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home", "/account/*", "/board/*" })
public class DemoWebFrontController extends HttpServlet {
	
	private DemoWebControllerFactory controllerFactory = new DemoWebControllerFactory();
	
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
//		Controller controller = null;
//		switch (action) {
//		case "home": 
//			controller = new HomeController();
//			break;
//		case "account/login": 
//			controller = new LoginController();
//			break;
//		case "account/register": 
//			controller = new RegisterController();
//			break;
//		case "account/logout": break;
//		}
		Controller controller = controllerFactory.getInstance(action);
		ActionResult ar = controller.handleRequest(req, resp);
		
		// 3. 뷰 선택 및 호출 ( JSP로 forward or 다른 Controller로 redirect )
		// 3-1. 응답 컨텐츠 생산
		String viewName = ar.getViewName();
		if (ar.isResponseBody()) {
			// do nothing
		} else if (ar.isRedirect()) {
			resp.sendRedirect(viewName);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
			rd.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}












