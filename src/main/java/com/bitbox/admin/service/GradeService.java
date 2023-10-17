package com.bitbox.admin.service;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.dto.GradeDto;
import com.bitbox.admin.exception.InvalidClassIdException;
import com.bitbox.admin.exception.InvalidExamIdException;
import com.bitbox.admin.repository.AdminInfoRepository;
import com.bitbox.admin.repository.ClassInfoRepository;
import com.bitbox.admin.repository.ExamInfoRepository;
import com.bitbox.admin.repository.GradeInfoRepository;
import com.bitbox.admin.service.response.GradeInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeService {
    private final GradeInfoRepository gradeInfoRepository;
    private final AdminInfoRepository adminInfoRepository;
    private final ClassInfoRepository classInfoRepository;
    private final ExamInfoRepository examInfoRepository;
    @Transactional
    public Grade registerGradeInfo(GradeDto gradeDto, "유효한 학생 id 리스트") {
        Classes classes = classInfoRepository.findById(gradeDto.getClassId()).orElseThrow(()-> new InvalidClassIdException("존재하지 않는 클래스 정보"));
        Exam exam = examInfoRepository.findById(gradeDto.getExamId()).orElseThrow(()->new InvalidExamIdException("존재하지 않는 시험 정보"));
        // 이 안에서는 검증 필요 X
        return gradeInfoRepository.save(GradeDto.convertGradeDtoToGrade(gradeDto, exam, classes));
    }

    @Transactional(readOnly = true)
    public List<Grade> getGradeInfosByClassId(Long classId){
            List<Grade> grades = gradeInfoRepository.findAllByClasses_ClassIdAndDeletedIsFalse(classId);
            return grades;
    }

    @Transactional
    public List<GradeInfoResponse> getGradeInfoByMemberId(String memberId){
        List<Grade> grades = gradeInfoRepository.findAllByMemberIdAndDeletedIsFalse(memberId);
        List<GradeInfoResponse> gradeResults = new ArrayList<>();
        for(Grade grade: grades){
            gradeResults.add(GradeInfoResponse.convertGradeToGradeResponse(grade));
        }
        return gradeResults;
    }
}
