package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.domain.Grade;
import lombok.Getter;

import java.util.List;

@Getter
public class GradesAddDto {
    Long classId;
    Long examId;
    List<MemberExamDto> members;
}
