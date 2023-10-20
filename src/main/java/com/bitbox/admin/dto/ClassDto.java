package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Classes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
public class ClassDto {
    @NotEmpty(message = "필수 입력값입니다")
    private String className;

    @NotEmpty(message = "필수 입력값입니다")
    private String classCode;

    public Classes convertExamDtoToExam(ClassDto classDto){
        return Classes.builder().className(classDto.getClassName())
                .classCode(classDto.getClassCode())
                .build();
    }
}
