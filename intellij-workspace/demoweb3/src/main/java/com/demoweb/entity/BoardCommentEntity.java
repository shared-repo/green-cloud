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
@Table(name = "tbl_boardcomment")
public class BoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNo;

//    @Column(nullable = false)
//    private int boardNo;

    @Column(nullable = false, length = 500)
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
    private boolean deleted = false;

    @Column
    private int groupNo;
    @Column(nullable = false)
    private int step;
    @Column(nullable = false)
    private int depth;
}
