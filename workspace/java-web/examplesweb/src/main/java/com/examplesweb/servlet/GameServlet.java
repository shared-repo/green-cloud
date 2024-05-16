package com.examplesweb.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/dogame" })
public class GameServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 데이터 읽기
		String userStr = req.getParameter("user");
		int you = Integer.parseInt(userStr);
		
		// 2. 요청 데이터 처리
		int com = (int) (Math.random() * 3) + 1; // 1, 2, 3

		String result = "";
		if ((you == 1 && com == 3) || (you == 2 && com == 1) || (you == 3 && com == 2)) {
			result = "이겼습니다.";
		} else if (you == com) {
			result = "비겼습니다.";
		} else {
			result = "졌습니다.";
		}
		
		// 3. JSP에서 사용할 수 있도록 데이터를 req에 저장
		req.setAttribute("result", result);
		
		// 3. 응답 컨텐츠 생산 + 응답 --> JSP로 forward
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("05.game-result.jsp");
		dispatcher.forward(req, resp);
	}
	

}
