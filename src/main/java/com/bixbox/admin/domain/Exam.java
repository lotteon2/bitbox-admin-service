package com.bixbox.admin.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="exam")
public class Exam {
    @Id
    @GeneratedValue
    private Long examId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @Column(nullable = false)
    private String examName;

    @Column(nullable = false)
    private Long perfectScore;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;
}
