package com.bitbox.admin.controller;

import com.bitbox.admin.dto.ExamDto;
import com.bitbox.admin.dto.ExamUpdateDto;
import com.bitbox.admin.service.ExamService;
import com.bitbox.admin.domain.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // 시험 추가 (반, 시험 이름, 만점)
    @PostMapping("")
    public ResponseEntity<Exam> registerExam(@Valid @RequestBody ExamDto examDto){
        return ResponseEntity.ok(examService.registerExamInfo(examDto));
    }

    // 특정 반 시험 조회
    @GetMapping("/{class_id}")
    @ResponseBody
    public ResponseEntity<Exam> getExamInfoByClassId(@PathVariable Long class_id) {
        return ResponseEntity.ok(examService.getExamInfoByClassId(class_id));
    }

    // 시험 수정 및 삭제
    @PatchMapping("")
    public ResponseEntity<Boolean> updateExamInfo(@Valid @RequestBody ExamUpdateDto examUpdateDto){
        return ResponseEntity.ok(examService.updateExamInfo(examUpdateDto));
    }
}
