package com.demoweb.service;

import com.demoweb.dto.MemberDto;

public interface AccountService {

	// 회원 가입 처리
	void registerMember(MemberDto member);

	//public MemberDto findMemberByMemeberIdAndPasswd(String memberId, String passwd) {
	MemberDto findMemberByMemeberIdAndPasswd(MemberDto member);

	void resetPasswd(MemberDto member);

	boolean checkDuplication(String memberId);

}