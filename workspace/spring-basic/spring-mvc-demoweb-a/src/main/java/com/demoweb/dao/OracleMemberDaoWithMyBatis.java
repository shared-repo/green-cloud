package com.demoweb.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class OracleMemberDaoWithMyBatis implements MemberDao {
	
	@Setter
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insertMember(MemberDto member) {
		
		sqlSessionTemplate.insert("com.demoweb.mapper.MemberMapper.insertMember", member);
		
	}
	
	public MemberDto selectMemberByMemberIdAndPasswd(MemberDto member) {
		
		MemberDto selectedMember = 
			sqlSessionTemplate.selectOne("com.demoweb.mapper.MemberMapper.selectMemberByIdAndPasswd", member);
		
		return selectedMember; // 조회 결과를 저장한 객체 반환
		
	}
	
	public void updatePasswd(MemberDto member) {
		sqlSessionTemplate.update("com.demoweb.mapper.MemberMapper.updatePasswd", member);
	}

	public int selectMemberCountByMemberId(String memberId) {
		int count = sqlSessionTemplate.selectOne(
				"com.demoweb.mapper.MemberMapper.selectMemberCountByMemberId", memberId);		
		return count; // 조회 결과를 저장한 변수 반환
	}

}
























