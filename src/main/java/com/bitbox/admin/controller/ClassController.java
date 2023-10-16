package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.dto.ClassDto;
import com.bitbox.admin.dto.ClassUpdateDto;
import com.bitbox.admin.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/class")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClassController {
    private final ClassService classService;

    /**
     * 반 추가(반 이름, 반 코드)
     */
    @PostMapping("")
    public ResponseEntity<Long> registerClassInfo(@Valid @RequestBody ClassDto classDto){
        return ResponseEntity.ok(classService.registerClassInfo(classDto));
    }

    /**
     * @param classId
     * 클래스 정보 수정 (수료 여부, 이름)
     */
    @PatchMapping("/{classId}")
    public ResponseEntity<Boolean> updateClassInfo(@PathVariable Long classId, @RequestBody ClassUpdateDto classUpdateDto){
        return ResponseEntity.ok(classService.updateClassInfo(classId, classUpdateDto));
    }

    /**
        클래스ID에 해당하는 클래스 받아오기 (classId 0번이면 전체 조회)
     */
    @GetMapping("")
    public ResponseEntity<List<Classes>> getClassInfoByClassId(@RequestParam(value = "classId") Long classId) {
        return ResponseEntity.ok(classService.getClassInfoByClassId(classId));
    }
}
