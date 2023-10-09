package com.bixbox.admin.domain;

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
}
