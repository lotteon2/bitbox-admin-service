package com.bitbox.admin.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamInfoResponse {
    private Long examId;
    private String examName;
    private Long classId;
    private String className;
    private Long perfectScore;
    private Double avg;
}
