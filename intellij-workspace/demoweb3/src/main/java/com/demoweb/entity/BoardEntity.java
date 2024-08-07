package com.demoweb.entity;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Many;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @TableGenerator(name="board_seq", allocationSize = 1)
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

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    // @JoinColumn(name = "boardNo")
    private List<BoardAttachEntity> attachments;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "boardNo")
    private List<BoardCommentEntity> comments;

}









