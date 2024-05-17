package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.demoweb.dto.MemberDto;

public class AccountDao {
	
	// 회원가입 처리 -> 회원정보를 데이터베이스에 저장
	public void insertMember(MemberDto member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "INSERT INTO member (memberid, passwd, email) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			pstmt.executeUpdate(); // insert, update, delete sql은 executeUpdate로 실행
			
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

}
