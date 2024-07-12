package com.demoweb.repository;

import com.demoweb.entity.BoardAttachEntity;
import com.demoweb.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    @Query(value = "SELECT ba FROM BoardAttachEntity ba WHERE ba.attachNo = :attachNo")
    BoardAttachEntity findBoardAttachByAttachNo(@Param("attachNo") int attachNo);
}
