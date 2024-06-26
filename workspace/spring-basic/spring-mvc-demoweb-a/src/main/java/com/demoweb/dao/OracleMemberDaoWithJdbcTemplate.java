package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;

import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class OracleMemberDaoWithJdbcTemplate implements MemberDao {
	
	@Setter
	private DataSource dataSource;
	
	@Setter
	private JdbcTemplate jdbcTemplate;
	
	// 회원가입 처리 -> 회원정보를 데이터베이스에 저장
	public void insertMember(MemberDto member) {
		
		String sql = "INSERT INTO member (memberid, passwd, email) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, member.getMemberId(), member.getPasswd(), member.getEmail());
			
	}
	
	// public MemberDto selectMemberByMemberIdAndPasswd(String memberId, String passwd) {
	public MemberDto selectMemberByMemberIdAndPasswd(MemberDto member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto selectedMember = null; // 조회 결과를 저장할 변수
		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기			
			conn = dataSource.getConnection();
			
			// 3. 명령 객체 만들기
			String sql = "SELECT memberid, email, usertype, regdate, active " +
						 "FROM member " +
						 "WHERE memberid = ? AND passwd = ? ";
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memberId);
//			pstmt.setString(2, passwd);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPasswd());
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리
			if (rs.next()) {
				selectedMember = new MemberDto(); // 조회 결과를 저장할 객체 생성
				selectedMember.setMemberId(rs.getString(1)); // 객체에 조회한 각 값을 저장
				selectedMember.setEmail(rs.getString(2));
				selectedMember.setUserType(rs.getString(3));
				selectedMember.setRegDate(rs.getDate(4));
				selectedMember.setActive(rs.getBoolean(5));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
		
		return selectedMember; // 조회 결과를 저장한 객체 반환
		
	}
	
	public void updatePasswd(MemberDto member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기			
			conn = dataSource.getConnection();
			
			// 3. 명령 객체 만들기
			String sql = "UPDATE member SET passwd = ? WHERE memberid = ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getMemberId());
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			int affectedCount = pstmt.executeUpdate(); // insert, update, delete sql은 executeUpdate로 실행 -> 반환 값은 영향 받은 행의 갯수
			System.out.printf("수정된 행의 갯수 : %d\n", affectedCount);
			
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
	}

	public int selectMemberCountByMemberId(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0; // 조회 결과를 저장할 변수
		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기			
			conn = dataSource.getConnection();
			
			// 3. 명령 객체 만들기
			String sql = "SELECT COUNT(memberId) " +
						 "FROM member " +
						 "WHERE memberid = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리
			rs.next();
			count = rs.getInt(1);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
		
		return count; // 조회 결과를 저장한 변수 반환
	}

}
























