package com.bitbox.admin.domain;

import com.bitbox.admin.domain.key.ClassAdminId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "class_admin")
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
