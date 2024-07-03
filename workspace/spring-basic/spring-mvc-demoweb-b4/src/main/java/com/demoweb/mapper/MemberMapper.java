package com.demoweb.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demoweb.dto.MemberDto;

// MyBatis DAO 객체 생성할 때 적용할 인터페이스
@Mapper // root-context.xml 파일의 <bean id="memberMapper" ... 을 대신하는 annotation
public interface MemberMapper {

	@Insert("INSERT INTO member (memberid, passwd, email) " +
			"VALUES (#{ memberId }, #{ passwd }, #{ email })")
	void insertMember(MemberDto member);
	
	@Select("SELECT memberId, email, userType, regDate, active " +
			"FROM member " +
			"WHERE memberId = #{ memberId } AND passwd = #{ passwd }")
	@Results(id = "memberResultMap",
			 value = {
					   @Result(column = "memberId", property = "memberId", id = true),
					   @Result(column = "email", property = "email"),
					   @Result(column = "userType", property = "userType"),
					   @Result(column = "regDate", property = "regDate"),
					   @Result(column = "active", property = "active")
			   })
	MemberDto selectMemberByMemberIdAndPasswd(MemberDto member);
	
	@Select("SELECT memberId, email, userType, regDate, active " +
			"FROM member " +
			"WHERE memberId = #{ memberId } AND passwd = #{ passwd }")
	MemberDto selectMemberByMemberIdAndPasswd2(@Param("memberId") String memberId, @Param("passwd") String passwd);
	
	@Update("UPDATE member SET passwd = #{ passwd } WHERE memberid = #{ memberId }")
	void updatePasswd(MemberDto member);
	
	@Select("SELECT COUNT(memberId) " +
			"FROM member " +
			"WHERE memberid = #{ memberId }")
	int selectMemberCountByMemberId(@Param("memberId") String memberId);
}
