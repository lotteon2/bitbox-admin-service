package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.dto.GradeDto;
import com.bitbox.admin.service.GradeService;
import com.bitbox.admin.service.response.GradeInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/grade")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GradeController {
    private final GradeService gradeService;

    /**
     *
     * @param gradeDto
     * @return Grade
     * 시험,학생,반정보를 받아서 점수를 등록
     */
    @PostMapping("")
    public ResponseEntity<Grade> registerGradeInfo(@Valid @RequestBody GradeDto gradeDto){
//        gradeDto.getMemberId(), classId
        //정윤에게 유효한 학생 정보 리스트 받아오기
        return ResponseEntity.ok(gradeService.registerGradeInfo(gradeDto, "유효한 학생 id 리스트"));
    }

    /**
     *
     * @param classId
     * @return List<Grade>
     */
    @GetMapping("/{classId}")
    public ResponseEntity<List<Grade>> getGradeInfosByClassId(@PathVariable Long classId){
        return ResponseEntity.ok(gradeService.getGradeInfosByClassId(classId));
    }

    /**
     *
     * @param memberId
     * @return
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<List<GradeInfoResponse>> getGradesByMemberId(@PathVariable String memberId){
        return ResponseEntity.ok(gradeService.getGradeInfoByMemberId(memberId));
    }
}
