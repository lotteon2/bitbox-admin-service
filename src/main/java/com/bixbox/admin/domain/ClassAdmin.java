package com.bixbox.admin.domain;

import com.bixbox.admin.domain.key.ClassAdminId;

import javax.persistence.*;

@Table(name = "class_admin")
@Entity
public class ClassAdmin {
    @EmbeddedId
    private ClassAdminId id;

    @MapsId("classId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classes;

    @MapsId("adminId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
