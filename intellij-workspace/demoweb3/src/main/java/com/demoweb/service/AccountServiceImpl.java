package com.demoweb.service;

import com.demoweb.common.Util;
import com.demoweb.dto.MemberDto;
import com.demoweb.entity.MemberEntity;
import com.demoweb.mapper.MemberMapper;

import com.demoweb.repository.MemberRepository;
import lombok.Setter;

import java.util.Optional;
import java.util.OptionalInt;

public class AccountServiceImpl implements AccountService {

	@Setter
	private MemberRepository memberRepository;
	
	// 회원 가입 처리
	@Override
	public void registerMember(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");
		member.setPasswd(hashedPasswd);

		MemberEntity memberEntity = member.toEntity();
		memberRepository.save(memberEntity); // entity 저장 --> insert or update
		
	}
	
	//public MemberDto findMemberByMemeberIdAndPasswd(String memberId, String passwd) {
	@Override
	public MemberDto findMemberByMemeberIdAndPasswd(MemberDto member) {
		
		// 업무 규칙(요구사항) 처리
		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");

		MemberEntity foundMemberEntity =
				memberRepository.findMemberByMemberIdAndPasswd(member.getMemberId(), hashedPasswd);
		MemberDto foundMember = MemberDto.of(foundMemberEntity);
		// 호출한 곳으로 조회 결과 반환
		return foundMember;
	}
	
	@Override
	public void resetPasswd(MemberDto member) {

		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");
		MemberEntity memberEntity = memberRepository.findById(member.getMemberId()).get();
		memberEntity.setPasswd(hashedPasswd);
		memberRepository.save(memberEntity); // save --> insert or update
	}

	@Override
	public boolean checkDuplication(String memberId) {
		 return memberRepository.findById(memberId).isEmpty();
	}

}











