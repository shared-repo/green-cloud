package com.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.demoweb.dao.MemberDao;
import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;
import com.demoweb.service.AccountServiceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@RequestMapping(path = "/account")
public class AccountController {
	
//	// 필드 선언 + 세터 주입 (직접)
//	private AccountService accountService;
//	@Autowired
//	public void setAccountService(AccountService accountService) {
//		this.accountService = accountService;
//	}
	
	// 필드 선언 + 세터 주입 (간접)
	@Setter(onMethod_ = { @Autowired } )
	private AccountService accountService;
	
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
	public String login(MemberDto member, HttpSession session) {
		
		// 로그인 처리
		MemberDto loginMember = accountService.findMemberByMemeberIdAndPasswd(member);
		
		if (loginMember != null) {
			session.setAttribute("loginuser", loginMember);
			// return new RedirectView("/spring-demoweb/home");
			return "redirect:/home";
		} else {
			return "redirect:login?loginfail=true";
		}
	}

}
