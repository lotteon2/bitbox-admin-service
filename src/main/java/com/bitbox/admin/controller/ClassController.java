package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.dto.ClassDto;
import com.bitbox.admin.dto.ClassInfoWithAdminList;
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
        class 전체 조회
     */
    @GetMapping("")
    public ResponseEntity<List<ClassInfoWithAdminList>> getAllClassInfo() {
        return ResponseEntity.ok(classService.getAllClassInfo());
    }

//    /**
//     * adminId(memberId)를 통해 본인이 담당하고 있는 반 리스트 조회
//     */
//    @GetMapping("/myclass")
//    public ResponseEntity<List<Classes>> getMyClassInfo(@Valid @RequestHeader String memberId, AuthorityType authorityType){
//
//    }

}
