package com.bitbox.admin.domain;

import com.bitbox.admin.domain.key.ClassAdminId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name = "class_admin")
@Entity
public class ClassAdmin {
    @EmbeddedId
    private ClassAdminId id;

    @JsonIgnore
    @MapsId("classId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @JsonIgnore
    @MapsId("adminId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
