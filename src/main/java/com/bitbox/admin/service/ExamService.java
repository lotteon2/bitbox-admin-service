package com.bitbox.admin.service;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.domain.MemberInfo;
import com.bitbox.admin.dto.ExamDto;
import com.bitbox.admin.dto.ExamUpdateDto;
import com.bitbox.admin.exception.DuplicationException;
import com.bitbox.admin.exception.InvalidAdminIdException;
import com.bitbox.admin.exception.InvalidClassIdException;
import com.bitbox.admin.exception.InvalidExamIdException;
import com.bitbox.admin.repository.ClassInfoRepository;
import com.bitbox.admin.repository.ExamInfoRepository;
import com.bitbox.admin.repository.GradeInfoRepository;
import com.bitbox.admin.repository.MemberInfoRepository;
import com.bitbox.admin.service.response.ExamInfoResponse;
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
public class ExamService {
    private final ExamInfoRepository examInfoRepository;
    private final ClassInfoRepository classInfoRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;

    @Transactional
    public Long registerExamInfo(ExamDto examDto) {
        if (examInfoRepository.existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(examDto.getClassId(), examDto.getExamName())) {
            throw new DuplicationException("ERROR100 - 중복 시험 에러");
        }

        Classes classes = classInfoRepository.findById(examDto.getClassId()).orElseThrow(()-> new InvalidAdminIdException("존재하지 않는 클래스 아이디입니다."));

        Exam examResult = examInfoRepository.save(examDto.convertExamDtoToExam(examDto, classes));

        List<Grade> gradeList = new ArrayList<>();

        List<MemberInfo> memberIdByClassId = memberInfoRepository.findByClasses_ClassId(examResult.getClasses().getClassId());
        for(MemberInfo memberInfo : memberIdByClassId){
            gradeList.add(Grade.builder()
                    .classes(classes)
                    .exam(examResult)
                    .score(examDto.getPerfectScore())
                    .memberInfo(memberInfo)
                    .build());
        }

        gradeInfoRepository.saveAll(gradeList);
        return examResult.getExamId();
    }

    public List<ExamInfoResponse> getExamInfoByClassId(Long classId){
        Classes classes = classInfoRepository.findById(classId).orElseThrow(() -> new InvalidClassIdException("존재하지 않는 클래스 아이디입니다."));
        return examInfoRepository.findExamByClasses_ClassIdAndDeletedIsFalse(classId);
    }
    @Transactional
    public boolean updateExamInfo(Long examId, ExamUpdateDto examUpdateDto){
        Exam exam = examInfoRepository.findById(examId).orElseThrow(()-> new InvalidExamIdException("존재하지 않는 시험 아이디입니다."));
        examUpdateDto.convertExamInfoForUpdate(exam, examUpdateDto);
        return true;
    }
}
