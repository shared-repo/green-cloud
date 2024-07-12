package com.demoweb.repository;

import com.demoweb.entity.BoardAttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardAttachRepository extends JpaRepository<BoardAttachEntity, Integer> {

    @Modifying
    @Transactional
    @Query( value = "INSERT INTO BoardAttachEntity (boardNo, userFileName, savedFileName, downloadCount) "+
            "VALUES (:boardNo, :userFileName, :savedFileName, 0) ")
    void insertBoardAttach(@Param("boardNo") int boardNo,
                           @Param("userFileName") String userFileName,
                           @Param("savedFileName") String savedFileName);
}
