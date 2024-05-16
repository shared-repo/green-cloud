package com.examplesweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = { "/process-data" })
public class ProcessDataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.getParameter : 브라우저에서 전송한 요청 데이터 읽기
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.printf("%s / %s\n", name, email);
		
		// 응답 컨텐츠의 종류와 문자셋 설정
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();	// network stream을 대상으로 하는 IO 객체
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println("Your Name : " + name);
		out.println(" / ");
		out.println("Your Email : " + email);
		out.println("</h1>");

		out.println("</body>");
		out.println("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}

}
















