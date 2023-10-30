package com.bitbox.admin.domain;

import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="member_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfo {
    @Id
    @Column(nullable = false)
    private String memberId;

    @Column
    private String memberName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityType memberAuthority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classId;
}
