package com.demoweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/account/login" })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/account/login.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		
		// 2. 요청 데이터 처리 ( 데이터베이스에서 데이터 조회 )
		// System.out.printf("[%s][%s]\n", memberId, passwd);
		
		// memberId와 passwd로 회원정보(모든 컬럼) 조회하고 결과를 console에 출력하는 JDBC 코드 구현
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "SELECT memberid, email, usertype, regdate, active " +
						 "FROM member " +
						 "WHERE memberid = ? AND passwd = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, passwd);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리
			if (rs.next()) {
				System.out.printf("[%s][%s][%s][%s][%b]\n", 
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getBoolean(5));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		
		
		// 3. home으로 이동 ( 다른 서블릿으로 이동 -> redirect로 이동 )
		//    jsp로 이동 ( forward로 이동 )
		resp.sendRedirect("/demoweb/home");
		
	}
	
}























