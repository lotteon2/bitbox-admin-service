package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.dto.GradeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GradeInfoResponse {
    private Long examId;
    private String examName;
    private Long perfectScore;
    private Long score;
    private Long gradeId;
    private Double avgScore;

    public static GradeInfoResponse convertGradeToGradeResponse(Grade grade, Double avgScore){
        return GradeInfoResponse.builder()
                .examId(grade.getExam().getExamId())
                .gradeId(grade.getGradeId())
                .examName(grade.getExam().getExamName())
                .perfectScore(grade.getExam().getPerfectScore())
                .score(grade.getScore())
                .avgScore(avgScore)
                .build();
    }
}
