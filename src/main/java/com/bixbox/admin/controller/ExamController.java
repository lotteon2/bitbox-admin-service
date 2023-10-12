package com.bixbox.admin.controller;

import com.bixbox.admin.dto.ExamDto;
import com.bixbox.admin.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping("")
    public ResponseEntity<String> registerExam(@Valid @RequestBody ExamDto examDto){
        return ResponseEntity.ok(examService.registerExamInfo(examDto));
    }

}
