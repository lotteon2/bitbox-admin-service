package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.dto.GradeDto;
import com.bitbox.admin.dto.GradesAddDto;
import com.bitbox.admin.dto.MemberExamDto;
import com.bitbox.admin.exception.InvalidAdminIdException;
import com.bitbox.admin.feign.FeignServiceClient;
import com.bitbox.admin.service.GradeService;
import com.bitbox.admin.service.response.GradeByClassIdInfoResponse;
import com.bitbox.admin.service.response.GradeInfoResponse;
import io.github.bitbox.bitbox.dto.MemberTraineeResult;
import io.github.bitbox.bitbox.dto.MemberValidDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/grade")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GradeController {
    private final GradeService gradeService;
    private final FeignServiceClient feignServiceClient;
    /**
     *
     * @param gradeDto
     * @return Grade
     * 시험,학생,반정보를 받아서 점수를 등록
     */

    @PostMapping("")
    public ResponseEntity<MemberTraineeResult> registerGradeInfo(@Valid @RequestBody GradesAddDto gradesAddDto){
        List<MemberValidDto> memberValidDtoList = new ArrayList<>();
        for (MemberExamDto memberExamDto : gradesAddDto.getMembers()){
            memberValidDtoList.add(MemberValidDto.builder()
                            .classId(gradesAddDto.getClassId())
                            .memberId(memberExamDto.getMemberId())
                            .build());
        }
        ResponseEntity<MemberTraineeResult> result = feignServiceClient.getMemberIsValidToAddExam(memberValidDtoList);
        return ResponseEntity.ok(gradeService.registerGradeInfo(result.getBody(), gradesAddDto));
    }

    /**
     *
     * @param classId
     * @return List<Grade>
     * 시험별 성적조회
     */
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<GradeByClassIdInfoResponse>> getGradeInfosByClassId(@PathVariable Long classId){
        return ResponseEntity.ok(gradeService.getGradeInfosByClassId(classId));
    }

    /**
     *
     * @param memberId
     * @return
     * 관리자용 학생별 성적조회
     *
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
