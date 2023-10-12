package com.bitbox.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "classes")
@Entity
@DynamicInsert
@Getter
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @JsonIgnore
    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private List<ClassAdmin> classAdmins;

    @JsonIgnore
    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @JsonIgnore
    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    private List<Exam> exams;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String classCode;

    @CreatedDate
    @ColumnDefault("now()")
    @Column(nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isGraduate;
}