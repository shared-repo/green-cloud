package com.demoweb.service;

import com.demoweb.dao.AccountDao;
import com.demoweb.dto.MemberDto;

public class AccountService {
	
	// 회원 가입 처리
	public void registerMember(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		
		// 데이터베이스에 데이터 저장 ( Dao 호출 )
		AccountDao accountDao = new AccountDao();
		accountDao.insertMember(member);
		
	}
	
	//public MemberDto findMembeByMemeberIdAndPasswd(String memberId, String passwd) {
	public MemberDto findMembeByMemeberIdAndPasswd(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		
		// 데이터베이스에서 데이터 조회
		AccountDao accountDao = new AccountDao();
		// MemberDto foundMember = accountDao.selectMemberByMemberIdAndPasswd(memberId, passwd);
		MemberDto foundMember = accountDao.selectMemberByMemberIdAndPasswd(member);
		
		// 호출한 곳으로 조회 결과 반환
		return foundMember;
	}

}











