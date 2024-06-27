package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.JdbcUtils;

import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class OracleMemberDaoWithNamedParameterJdbcTemplate implements MemberDao {
	
	@Setter
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	// 회원가입 처리 -> 회원정보를 데이터베이스에 저장
	public void insertMember(MemberDto member) {
		
		String sql = "INSERT INTO member (memberid, passwd, email) VALUES (:memberId, :passwd, :email)";

//		Map<String, Object> params = new HashMap<>();
//		params.put("memberId", member.getMemberId());
//		params.put("passwd", member.getPasswd());
//		params.put("email", member.getEmail());
//		jdbcTemplate.update(sql, params);
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(member); // member의 getMethod로 자동 연결
		jdbcTemplate.update(sql, params);
	}
	
	public MemberDto selectMemberByMemberIdAndPasswd(MemberDto member) {
		
		String sql = "SELECT memberid, email, usertype, regdate, active " +
					 "FROM member " +
					 "WHERE memberid = :memberId AND passwd = :passwd ";
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(member);
		
		MemberDto selectedMember = 
			jdbcTemplate.queryForObject(sql, 
										params,
										// 1. RowMapper 인터페이스를 구현하는 이름 없는 클래스 만들기 + 2. 그 클래스의 인스턴스 만들기
										new RowMapper<MemberDto>() {	
											@Override
											public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
												MemberDto member = new MemberDto();
												member.setMemberId(rs.getString(1));
												member.setEmail(rs.getString(2));
												member.setUserType(rs.getString(3));
												member.setRegDate(rs.getDate(4));
												member.setActive(rs.getBoolean(5));
												return member;
											}
										});
		
		return selectedMember; // 조회 결과를 저장한 객체 반환
		
	}
	
	public void updatePasswd(MemberDto member) {
		
		String sql = "UPDATE member SET passwd = :passwd WHERE memberid = :memberId";
		SqlParameterSource params = new BeanPropertySqlParameterSource(member);
		jdbcTemplate.update(sql, params);
		
	}

	public int selectMemberCountByMemberId(String memberId) {

		String sql = "SELECT COUNT(memberId) " +
					 "FROM member " +
					 "WHERE memberid = :memberId ";

		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
	
		int count = jdbcTemplate.queryForObject(sql,
												params,
												new RowMapper<Integer>() {
													@Override
													public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
														return rs.getInt(1);
													}
												});
			
		return count; // 조회 결과를 저장한 변수 반환
	}

}
























