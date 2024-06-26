package com.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.mail.internet.MimeMessage;
import lombok.Setter;

@Controller
@RequestMapping(path = { "/mail" })
public class MailController {
	
	@Setter(onMethod_ = { @Autowired })
	private JavaMailSender mailSender;
	
	@GetMapping(path = { "/send-mail" })
	public String sendMail(Model model) {
		
		boolean success = true;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			
			messageHelper.setFrom("tubeswim2@naver.com");
			messageHelper.setTo(new String[] { "oh.chi.hooooon@gmail.com", "shared.repo.z@gmail.com" });
			message.setSubject("메일 보내기 연습");
			message.setContent(String.format("<html><body><h1>%s</h1></body></html>", "메일을 확인해 주세요"), 
							   "text/html;charset=utf-8");
			
			mailSender.send(message);
		} catch (Exception ex) {
			success = false;
		}
		
		model.addAttribute("success", success);
		
		return "mail/send-mail-completion"; // /WEB-INF/views/ + mail/send-mail-completion + .jsp
	}

}
