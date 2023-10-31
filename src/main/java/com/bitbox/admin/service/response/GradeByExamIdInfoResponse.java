package com.bitbox.admin.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GradeByExamIdInfoResponse {
    private Long gradeId;
    private String memberId;
    private String memberName;
    private String examName;
    private Long examId;
    private Long score;
    private Long perfectScore;
}
