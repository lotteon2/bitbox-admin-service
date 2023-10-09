package com.bixbox.admin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="admin_class")
public class AdminClass {
    //TODO; 복합키 설정
    @EmbeddedId
    private AdminClassId id;

    @MapsId("adminId")
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @MapsId("classId")
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class class2;
}
