package com.bixbox.admin.domain;

<<<<<<< HEAD
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

=======
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
>>>>>>> origin/dev
