package com.bitbox.admin.service;

import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.dto.GradeUpdateDto;
import com.bitbox.admin.repository.AdminInfoRepository;
import com.bitbox.admin.repository.ClassInfoRepository;
import com.bitbox.admin.repository.ExamInfoRepository;
import com.bitbox.admin.repository.GradeInfoRepository;
import com.bitbox.admin.service.response.GradeByClassIdInfoResponse;
import com.bitbox.admin.service.response.GradeByExamIdInfoResponse;
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
//    @Transactional
//    public MemberTraineeResult registerGradeInfo(MemberTraineeResult result, GradesAddDto gradesAddDto) {
//        Classes classes = classInfoRepository.findById(gradesAddDto.getClassId()).orElseThrow(()-> new InvalidClassIdException("존재하지 않는 클래스 정보"));
//        Exam exam = examInfoRepository.findById(gradesAddDto.getExamId()).orElseThrow(()->new InvalidExamIdException("존재하지 않는 시험 정보"));
//
//        Map<String, Long> gradeMap = gradesAddDto.getMembers().stream().collect(Collectors.toMap(MemberExamDto::getMemberId, MemberExamDto::getScore));
//        for(MemberValidDto validMember: result.getValidMember()){
//            gradeInfoRepository.save(GradeDto.convertGradeDtoToGrade(validMember, exam, classes, gradeMap.get(validMember.getMemberId())));
//        }
//        return result;
//    }

    @Transactional
    public void updateGradeByGradeId(long gradeId, GradeUpdateDto gradeUpdateDto){
        Grade grade = gradeInfoRepository.findById(gradeId).orElseThrow(() -> new RuntimeException("존재하지 않는 시험번호 입니다."));
        grade.setScore(gradeUpdateDto.getScore());
    }

    @Transactional(readOnly = true)
    public List<GradeByClassIdInfoResponse> getGradeInfosByClassId(Long classId){
        List<GradeByClassIdInfoResponse> grades = gradeInfoRepository.findAllByClasses_ClassIdAndDeletedIsFalse(classId);
        return grades;
    }

    @Transactional(readOnly = true)
    public List<GradeByExamIdInfoResponse> getGradeInfosByExamId(Long examId){
        List<GradeByExamIdInfoResponse> grades = gradeInfoRepository.findAllByExam_ExamIdAndDeletedIsFalse(examId);
        return grades;
    }

    @Transactional(readOnly = true)
    public List<GradeInfoResponse> getMyGrades(String memberId){
        List<Grade> grades = gradeInfoRepository.findAllByMemberInfo_MemberIdAndDeletedIsFalse(memberId);
        List<GradeInfoResponse> gradeResults = new ArrayList<>();
        for(Grade grade: grades){
            Double avgScore = gradeInfoRepository.getAvgScoreByExamId(grade.getExam().getExamId());
            gradeResults.add(GradeInfoResponse.convertGradeToGradeResponse(grade, avgScore));
        }
        return gradeResults;
    }

    @Transactional
    public List<GradeInfoResponse> getGradeInfoByMemberId(String memberId){
        List<Grade> grades = gradeInfoRepository.findAllByMemberInfo_MemberIdAndDeletedIsFalse(memberId);
        List<GradeInfoResponse> gradeResults = new ArrayList<>();
        for(Grade grade: grades){
            Double avgScore = gradeInfoRepository.getAvgScoreByExamId(grade.getExam().getExamId());
            gradeResults.add(GradeInfoResponse.convertGradeToGradeResponse(grade, avgScore));
        }
        return gradeResults;
    }
}
