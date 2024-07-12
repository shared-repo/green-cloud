package com.demoweb.repository;

import com.demoweb.entity.BoardAttachEntity;
import com.demoweb.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

//    @Query(value =  "SELECT attachNo, userFileName, savedFileName, downloadCount " +
//                    "FROM tbl_boardattach " +
//                    "WHERE attachno = :attachNo ",
//            nativeQuery = true) // 실제 데이터베이스의 구문 규칙에 따라 작성한 SQL
    @Query(value = "SELECT ba FROM BoardAttachEntity ba WHERE ba.attachNo = :attachNo")
    BoardAttachEntity findBoardAttachByAttachNo(@Param("attachNo") int attachNo);
}
