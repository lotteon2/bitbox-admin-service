package com.bitbox.admin.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GradeByClassIdInfoResponse {
    private Long perfectScore;
    private Long score;
    private Long classId;
    private String examName;
    private String memberId;
}
