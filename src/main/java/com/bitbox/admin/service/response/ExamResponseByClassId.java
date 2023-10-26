package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Exam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseByClassId {
    private Long examId;
    private String examName;
    private Long perfectScore;

    public static ExamResponseByClassId from(Exam exam) {
        return ExamResponseByClassId.builder()
                .examId(exam.getExamId())
                .examName(exam.getExamName())
                .perfectScore(exam.getPerfectScore())
                .build();
    }

}
