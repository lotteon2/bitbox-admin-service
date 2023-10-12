package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.dto.ClassDto;
import com.bitbox.admin.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping("")
    public ResponseEntity<Long> registerClassInfo(@Valid @RequestBody ClassDto classDto){
        return ResponseEntity.ok(classService.registerClassInfo(classDto));
    }
}
