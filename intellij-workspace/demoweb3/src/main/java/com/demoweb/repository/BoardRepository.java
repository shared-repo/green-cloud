package com.demoweb.repository;

import com.demoweb.entity.BoardAttachEntity;
import com.demoweb.entity.BoardCommentEntity;
import com.demoweb.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    // @Query(value = "SELECT * FROM tbl_boardattach ba WHERE ba.attachNo = :attachNo", nativeQuery = true)
    @Query(value = "SELECT ba FROM BoardAttachEntity ba WHERE ba.attachNo = :attachNo")
    BoardAttachEntity findBoardAttachByAttachNo(@Param("attachNo") int attachNo);

    @Query(value = "SELECT bc FROM BoardCommentEntity bc WHERE bc.commentNo = :commentNo")
    BoardCommentEntity findBoardCommentByAttachNo(@Param("commentNo") int commentNo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BoardAttachEntity ba WHERE ba.attachNo = :attachNo")
    void deleteBoardAttachByAttachNo(@Param("attachNo")int attachNo);
}
