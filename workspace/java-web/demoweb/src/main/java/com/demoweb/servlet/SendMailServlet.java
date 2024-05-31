package com.demoweb.servlet;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @WebServlet("/mail/send-mail") // @WebServlet(value = "/mail/send-mail")
// @WebServlet(urlPatterns = "/mail/send-mail")
@WebServlet(urlPatterns = { "/mail/send-mail" })
public class SendMailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/mail/send-mail.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// String from = req.getParameter("from");
		String from = "tubeswim2@naver.com";
		String to = req.getParameter("to");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
			
		Session session = null;
		
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.naver.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", "smtp.naver.com");
			props.put("mail.smtp.starttls.required", true);
			props.put("mail.smtp.starttls.enable", true);
			
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {				
					return new PasswordAuthentication("", "");					
				}
			});
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("fail to create session");
			session = null;
		}
		
		if (session != null) {
			Message message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress(from, "instructor", "utf-8"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));				
				message.setSubject(title);
				message.setContent(String.format("<html><body><h1>%s</h1></body></html>", content), 
								   "text/html;charset=utf-8");
				
				Transport.send(message);
				System.out.println("succeeded to send mail");
				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("fail to send mail");
			}
		}
		
		
	}

}
