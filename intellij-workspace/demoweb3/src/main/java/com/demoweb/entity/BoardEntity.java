package com.demoweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardNo;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false)
    private String writer;

    @Builder.Default
    @Column
    private Date writeDate = new Date();
    @Builder.Default
    @Column
    private Date modifyDate = new Date();
    @Builder.Default
    @Column
    private int readCount = 0;
    @Builder.Default
    @Column
    private boolean deleted = false;

}









