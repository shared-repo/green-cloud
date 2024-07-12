package com.demoweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_boardattach")
public class BoardAttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attachNo;
//    @Column(nullable = false)
//    private int boardNo;
    @Column(nullable = false)
    private String userFileName;	// 사용자가 업로드한 파일 이름
    @Column(nullable = false)
    private String savedFileName;	// 서버에 저장한 파일 이름, 고유한 이름
    @Builder.Default
    @Column()
    private int downloadCount = 0;
}
