package com.bixbox.admin.domain;

import com.bixbox.admin.enums.UserAuthority;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="admin")
@Entity
@DynamicInsert
public class Admin {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String adminId;

    @OneToMany(mappedBy = "admin")
    private List<ClassAdmin> classAdmins;

    @Column(nullable = false)
    private String adminEmail;

    @Column(nullable = false)
    private String adminPassword;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String adminProfileImg;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthority adminAuthority;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;
}
