package com.demoweb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demoweb.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

	// Controller를 호출하기 전에 호출되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
							 Object handler) throws Exception {
		// System.out.println("AuthInterceptor.preHandle");
		MemberDto member = (MemberDto)request.getSession().getAttribute("loginuser");
		String uri = request.getRequestURI();		
		if (uri.contains("write") || uri.contains("edit") || uri.contains("delete")) {
			if (member == null) {
				String returnUri = uri.substring(1);			// /spring-demoweb/a/b/c --> spring-demoweb/a/b/c
				returnUri = returnUri.substring(returnUri.indexOf("/")); 	// spring-demoweb/a/b/c --> /a/b/c
				response.sendRedirect("/spring-demoweb/account/login?returnuri=" + returnUri);
				return false; // 컨트롤러 호출 수행 중단
			}
		}
		
		return true; // 컨트롤러 호출 수행 계속
	}
	
	// Controller를 호출한 후에 호출되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						   Object handler, ModelAndView modelAndView) throws Exception {
		// System.out.println("AuthInterceptor.postHandle");
	}
	// View를 호출한 후에 호출되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
								Object handler, Exception ex) throws Exception {
		// System.out.println("AuthInterceptor.afterCompletion");
	}
	
}
