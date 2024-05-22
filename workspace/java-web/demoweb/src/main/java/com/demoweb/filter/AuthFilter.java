package com.demoweb.filter;

import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" }) // default dispatcherType == DispatcherType.REQUEST
// @WebFilter(urlPatterns = { "/*" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class AuthFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		// System.out.println("필터 동작 시작");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String url = httpRequest.getRequestURI();
//		System.out.println(url);
		
		if (url.contains("/board/")) {
			if (url.contains("write")) {
				HttpSession session = httpRequest.getSession();
				if (session.getAttribute("loginuser") == null) { // 로그인하지 않은 요청이라면
					httpResponse.sendRedirect("/demoweb/account/login");
					return;
				}
			}
		}
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {	
		// System.out.println("필터 동작 중지");
	}	

}












