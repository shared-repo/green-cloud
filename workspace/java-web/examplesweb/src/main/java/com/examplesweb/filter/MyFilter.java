package com.examplesweb.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "*.jsp" })
public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req2 = (HttpServletRequest)req;
		String uri = req2.getRequestURI();
		
		System.out.printf("현재 요청 : %s\n", uri);
		
		if (uri.contains("hello")) {
			HttpServletResponse res2 = (HttpServletResponse)res;
			res2.sendRedirect("/examplesweb/index.html");
			return;
		}
		
		chain.doFilter(req, res); // 다음 필터 또는 서블릿 또는 JSP 등으로 요청 전달
		

	}

}
