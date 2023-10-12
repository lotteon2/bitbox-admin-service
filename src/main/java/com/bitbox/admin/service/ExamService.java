package com.bitbox.admin.service;

import com.bitbox.admin.repository.ExamInfoRepository;
import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.dto.ExamDto;
import com.bitbox.admin.dto.ExamUpdateDto;
import com.bitbox.admin.exception.DuplicationEmailException;
import com.bitbox.admin.exception.InvalidAdminIdException;
import com.bitbox.admin.repository.ClassInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamService {
    private final ExamInfoRepository examInfoRepository;
    private final ClassInfoRepository classInfoRepository;

    @Transactional
    public Exam registerExamInfo(ExamDto examDto) {
        if (examInfoRepository.existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(examDto.getClassId(), examDto.getExamName())) {
            throw new DuplicationEmailException("ERROR100 - 중복 시험 에러");
        }

        Classes classes = classInfoRepository.findById(examDto.getClassId()).orElseThrow(()-> new InvalidAdminIdException("존재하지 않는 클래스 아이디입니다."));

        Exam examResult = examInfoRepository.save(examDto.convertExamDtoToExam(examDto, classes));
        return examResult;
    }

    @Transactional(readOnly=true)
    public Exam getExamInfoByClassId(Long class_id){
        Exam exam = examInfoRepository.findById(class_id).orElseThrow(()-> new InvalidAdminIdException("존재하지 않는 클래스 아이디입니다."));
        return exam;
    }

    @Transactional
    public boolean updateExamInfo(ExamUpdateDto examUpdateDto){
        Exam exam = examInfoRepository.findById(examUpdateDto.getExamId()).orElseThrow(()-> new InvalidAdminIdException("존재하지 않는 시험 아이디입니다."));
        examUpdateDto.convertExamInfoForUpdate(exam, examUpdateDto);
        return true;
    }
}
