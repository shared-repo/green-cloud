package com.demoweb.service;

import com.demoweb.common.Util;
import com.demoweb.dto.MemberDto;
import com.demoweb.mapper.MemberMapper;

import lombok.Setter;

public class AccountServiceImpl implements AccountService {
	
	@Setter
	private MemberMapper memberMapper;
	
	// 회원 가입 처리
	@Override
	public void registerMember(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");
		member.setPasswd(hashedPasswd);
		
		// 데이터베이스에 데이터 저장 ( Dao 호출 )
		memberMapper.insertMember(member);
		
	}
	
	//public MemberDto findMemberByMemeberIdAndPasswd(String memberId, String passwd) {
	@Override
	public MemberDto findMemberByMemeberIdAndPasswd(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");
		// memberId = hashedPasswd;
		member.setPasswd(hashedPasswd);
		
		// 데이터베이스에서 데이터 조회
		// MemberDto foundMember = memberDao.selectMemberByMemberIdAndPasswd(memberId, passwd);
		MemberDto foundMember = memberMapper.selectMemberByMemberIdAndPasswd(member);
		
		// 호출한 곳으로 조회 결과 반환
		return foundMember;
	}
	
	@Override
	public void resetPasswd(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");
		member.setPasswd(hashedPasswd);
		
		memberMapper.updatePasswd(member);
		
	}

	@Override
	public boolean checkDuplication(String memberId) {
		int count = memberMapper.selectMemberCountByMemberId(memberId);
		return count == 0;
	}

}











