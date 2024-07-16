package com.demoweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tbl_member")
public class MemberEntity {
    @Id
    private String memberId;
    @Column
    private String passwd;
    @Column
    private String email;

    @Builder.Default // Builder를 사용해서 객체 만들 때 기본값 사용하는 설정
    @Column
    private String userType = "user";
    @Builder.Default
    @Column
    private Date regDate = new Date();
    @Builder.Default
    @Column
    private boolean active = true;

    @ManyToMany
    @JoinTable(
            name="tbl_member_role",
            joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "roleNo")
    )
    private Set<RoleEntity> roles;
}
