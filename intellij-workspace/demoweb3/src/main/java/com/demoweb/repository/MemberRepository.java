package com.demoweb.repository;

import com.demoweb.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    MemberEntity findMemberByMemberIdAndPasswd(String memberId, String passwd);

}
