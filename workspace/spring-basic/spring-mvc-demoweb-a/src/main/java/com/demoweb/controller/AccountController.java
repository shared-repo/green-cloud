package com.demoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demoweb.dao.MemberDao;
import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

@Controller
@RequestMapping(path = "/account")
public class AccountController {
	
	private AccountService accountService = new AccountService();
	
	// @RequestMapping(path = { "/account/register" })
	// @RequestMapping(path = { "/register" }) // GET OR POST OR PUT OR ...
	// @RequestMapping(path = { "/register" }, method = { RequestMethod.GET }) // only GET
	@GetMapping(path = { "/register" }) // only GET
	public String registerForm() {
		
		return "account/register";	// /WEB-INF/views/ + account/register + .jsp
	}
	
	@PostMapping(path = { "/register" })
	public String register(MemberDto member) {
		
		// 회원 가입 처리
		// System.out.println(member);
		accountService.registerMember(member);
		
		return "redirect:login";
	}
	
	
	// @RequestMapping(path = { "/login" })
	@GetMapping(path = { "/login" }) // only GET
	public String loginForm() {
		
		return "account/login";
	}
	
	@PostMapping(path = { "/login" })
	public String login(MemberDto member) {
		
		// 로그인 처리
		// System.out.println(member);
		MemberDto loginMember = accountService.findMemberByMemeberIdAndPasswd(member);
		System.out.println(loginMember);
		
		return "redirect:/home";
	}

}
