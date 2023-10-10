package com.bixbox.admin.domain;

import javax.persistence.*;

@Entity
@Table(name="grade")
public class Grade {
    @Id
    @GeneratedValue
    private Long gradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long score;
}