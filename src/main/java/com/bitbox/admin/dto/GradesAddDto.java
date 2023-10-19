package com.bitbox.admin.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GradesAddDto {
    Long classId;
    Long examId;
    List<MemberExamDto> members;
}
