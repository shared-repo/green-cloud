package com.demoweb.mapper;

import com.demoweb.dto.MemberDto;

// MyBatis Mapper.xml 파일과 연동해서 동작하는 DAO 객체 생성할 때 적용할 인터페이스
// Mapper.xml 파일과 동일하게 작성 (경로, 이름, 전달인자, 결과형, ... )
public interface MemberMapper {

	void insertMember(MemberDto member);
	MemberDto selectMemberByMemberIdAndPasswd(MemberDto member);
	// MemberDto selectMemberByMemberIdAndPasswd2(String memberId, String passwd);
	void updatePasswd(MemberDto member);
	int selectMemberCountByMemberId(String memberId);
}
