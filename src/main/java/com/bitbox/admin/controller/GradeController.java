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
    
    //TODO : 정윤이랑 유저검증 로직 추가
    @PostMapping("")
    public ResponseEntity<Grade> registerGradeInfo(@Valid @RequestBody GradeDto gradeDto){
        return ResponseEntity.ok(gradeService.registerGradeInfo(gradeDto));
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

    /**
     *
     * @param memberId
     * @return
     * 정윤이가 조회하는 내 성적 조회
     */
    @GetMapping("/mygrade")
    public ResponseEntity<List<GradeInfoResponse>> getMyGrades(@RequestHeader String memberId){
        return ResponseEntity.ok(gradeService.getMyGrades(memberId));
    }
}
