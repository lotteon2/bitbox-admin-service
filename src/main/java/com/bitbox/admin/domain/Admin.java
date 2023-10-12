package com.bitbox.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="admin")
@Entity
@DynamicInsert
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Admin {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String adminId;

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private List<ClassAdmin> classAdmins;

    @Column(nullable = false)
    private String adminEmail;

    // TODO : 암호화 추가
    @Column(nullable = false)
    @ColumnDefault("1111")
    private String adminPassword;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String adminProfileImg;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityType adminAuthority;

    @CreatedDate
    @ColumnDefault("now()")
    @Column(nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @ColumnDefault("now()")
    @Column(nullable = false,  columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ColumnDefault("false")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

}
