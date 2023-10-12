package com.bixbox.admin.dto;

import com.bixbox.admin.domain.Classes;
import com.bixbox.admin.domain.Exam;
import com.bixbox.admin.repository.ClassInfoRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class ExamDto {

    @NotEmpty(message = "필수 입력값입니다")
    private Long classId;
    @NotEmpty(message = "필수 입력값입니다")
    private String examName;
    @NotEmpty(message = "필수 입력값입니다")
    private Long perfectScore;

    // TODO: classID는 어떻게?
    public static Exam convertExamDtoToExam(ExamDto examDto){
        Classes classes = ClassInfoRepository.findById(examDto.getClassId());

        return Exam.builder()
                .examName(examDto.getExamName())
                .perfectScore(examDto.getPerfectScore())
                .classes(classes)
                .build();
    }
}
