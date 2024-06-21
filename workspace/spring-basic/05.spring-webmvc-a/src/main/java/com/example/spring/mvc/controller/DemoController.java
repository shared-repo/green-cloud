package com.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
	
//	@RequestMapping(path = { "/demo/param" })
//	public String processParam(HttpServletRequest req) {
//		
//		String data1 = req.getParameter("data1");
//		String sData2 = req.getParameter("data2");
//		int data2 = Integer.parseInt(sData2);
//		
//		System.out.println(data1 + " / " + data2);
//		
//		return "demo/result"; // /WEB-INF/views/ + demo/result + .jsp
//	}
	
//	@RequestMapping(path = { "/demo/param" })
//	public String processParam(@RequestParam("data1") String data1, @RequestParam("data2") int data2) {
//		
//		System.out.println(data1 + " / " + data2);
//		
//		return "demo/result"; // /WEB-INF/views/ + demo/result + .jsp
//	}
	
	// @RequestMapping(path = { "/demo/param" }) // GET, POST, ... 등 모든 요청 처리
	// @RequestMapping(path = { "/demo/param" }, method = RequestMethod.GET) // GET 요청만 처리
	@GetMapping(path = { "/demo/param" }) // GET 요청만 처리
	public String processParam(@RequestParam("data1") String data1, @RequestParam("data2") int data2) {
		
		System.out.println(data1 + " / " + data2);
		
		return "demo/result"; // /WEB-INF/views/ + demo/result + .jsp
	}

}









