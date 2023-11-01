package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.dto.ExamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class ClassInfoResponse {
    private Long classId;
    private String className;
    private String classCode;
    private List<ExamResponseByClassId> exams;

    public static ClassInfoResponse ClassInfoResponse(Classes classes) {
        return ClassInfoResponse.builder()
                .classId(classes.getClassId())
                .className(classes.getClassName())
                .classCode(classes.getClassCode())
                .exams(
                        classes.getExams().stream().filter(exam -> !exam.isDeleted())
                                .map(ExamResponseByClassId::from)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
