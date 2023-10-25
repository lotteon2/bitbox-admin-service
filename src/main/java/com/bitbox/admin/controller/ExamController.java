package com.bitbox.admin.controller;

import com.bitbox.admin.dto.ExamDto;
import com.bitbox.admin.dto.ExamUpdateDto;
import com.bitbox.admin.service.ExamService;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.service.response.ExamInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // 시험 추가 (반, 시험 이름, 만점)
    @PostMapping("")
    public ResponseEntity<Long> registerExam(@Valid @RequestBody ExamDto examDto){
        return ResponseEntity.ok(examService.registerExamInfo(examDto));
    }

    // 특정 반 시험 조회
    @GetMapping("/{classId}")
    public ResponseEntity<List<ExamInfoResponse>> getExamInfoByClassId(@PathVariable Long classId) {
        return ResponseEntity.ok(examService.getExamInfoByClassId(classId));
    }

    // 시험 수정 및 삭제
    @PatchMapping("/{examId}")
    public ResponseEntity<Boolean> updateExamInfo(@PathVariable Long examId, @Valid @RequestBody ExamUpdateDto examUpdateDto){
        return ResponseEntity.ok(examService.updateExamInfo(examId, examUpdateDto));
    }
}
