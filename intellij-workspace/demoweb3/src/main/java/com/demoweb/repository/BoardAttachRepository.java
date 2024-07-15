package com.demoweb.repository;

import com.demoweb.entity.BoardAttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardAttachRepository extends JpaRepository<BoardAttachEntity, Integer> {
}
