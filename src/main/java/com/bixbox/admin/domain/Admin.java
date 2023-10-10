package com.bixbox.admin.domain;

<<<<<<< HEAD
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private Long adminId;

    @OneToMany(mappedBy = "admin")
    private List<AdminClass> adminClasses = new ArrayList<>();

    @Column(name = "admin_email", nullable = false)
    private String adminEmail;

    @Column(name = "admin_password", nullable = false)
    private String adminPassword;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name = "admin_profile_img")
    private String adminProfileImg;

    @Column(name = "admin_authority", nullable = false)
    private String adminAuthority;

    @Column(name="created_at", nullable = false, columnDefinition = "timestamp default NOW()")
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false, columnDefinition = "timestamp default NOW()")
    private LocalDateTime updatedAt;

    @Column(name="is_deleted", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isDeleted;
=======
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
>>>>>>> origin/dev
}
