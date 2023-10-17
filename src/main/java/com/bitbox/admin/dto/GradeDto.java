package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.domain.Grade;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class GradeDto {
    @NotNull(message = "필수 입력값입니다")
    private Long classId;
    private Long examId;
    private String memberId;
    private Long score;

    public static Grade convertGradeDtoToGrade(GradeDto gradeDto, Exam exam, Classes classes){
        return Grade.builder()
                .exam(exam)
                .memberId(gradeDto.getMemberId())
                .classes(classes)
                .score(gradeDto.getScore())
                .build();
    }
}
