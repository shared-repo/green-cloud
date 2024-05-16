package com.examplesweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = { "/hello2" })
public class HelloServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();	// network stream을 대상으로 하는 IO 객체
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println("Hello, Servlet 2");
		out.println("</h1>");
		out.println("<h1>");
		out.println(new Date());
		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
