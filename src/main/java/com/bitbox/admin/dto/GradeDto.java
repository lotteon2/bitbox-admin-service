package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.domain.Grade;
import io.github.bitbox.bitbox.dto.MemberValidDto;
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

//    public static Grade convertGradeDtoToGrade(MemberValidDto memberValidDto, Exam exam, Classes classes, Long score){
//        return Grade.builder()
//                .exam(exam)
//                .memberId(memberValidDto.getMemberId())
//                .classes(classes)
//                .score(score)
//                .build();
//    }

}
