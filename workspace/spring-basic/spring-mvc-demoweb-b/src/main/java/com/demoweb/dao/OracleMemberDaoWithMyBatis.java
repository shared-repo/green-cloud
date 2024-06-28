package com.demoweb.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;

import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class OracleMemberDaoWithMyBatis implements MemberDao {
	
	@Setter
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String MEMBER_MAPPER = "com.demoweb.mapper.MemberMapper.";
	
	public void insertMember(MemberDto member) {
		
		// sqlSessionTemplate.insert("com.demoweb.mapper.MemberMapper.insertMember", member);
		sqlSessionTemplate.insert(MEMBER_MAPPER + "insertMember", member);
		
	}
	
	public MemberDto selectMemberByMemberIdAndPasswd(MemberDto member) {
		
		MemberDto selectedMember = 
			sqlSessionTemplate.selectOne(MEMBER_MAPPER + "selectMemberByIdAndPasswd", member);
		
		return selectedMember; // 조회 결과를 저장한 객체 반환
		
	}
	
	public MemberDto selectMemberByMemberIdAndPasswd2(String memberId, String passwd) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("passwd", passwd);
		
		MemberDto selectedMember = 
				sqlSessionTemplate.selectOne(MEMBER_MAPPER + "selectMemberByIdAndPasswd2", params);
			
		return selectedMember; // 조회 결과를 저장한 객체 반환
	}
	
	public void updatePasswd(MemberDto member) {
		sqlSessionTemplate.update(MEMBER_MAPPER + "updatePasswd", member);
	}

	public int selectMemberCountByMemberId(String memberId) {
		int count = sqlSessionTemplate.selectOne(MEMBER_MAPPER + "selectMemberCountByMemberId", memberId);		
		return count; // 조회 결과를 저장한 변수 반환
	}

}
























