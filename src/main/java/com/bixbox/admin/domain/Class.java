package com.bixbox.admin.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "class")
@Entity
@DynamicInsert
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private List<ClassAdmin> classAdmins;

    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private List<Exam> exams;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String classCode;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isGraduate;
}