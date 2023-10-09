package com.bixbox.admin.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="class")
public class Class {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name="class_id")
    private String classId;

    @OneToMany(mappedBy = "class2")
    private List<AdminClass> adminClasses = new ArrayList<>();

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name="class_code", nullable = false)
    private String classCode;

    @Column(name="created_at", nullable = false, columnDefinition = "timestamp default NOW()")
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false, columnDefinition = "timestamp default NOW()")
    private LocalDateTime updatedAt;

    @Column(name="is_graduate", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isGraduate;
}

