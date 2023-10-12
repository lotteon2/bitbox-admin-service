package com.bixbox.admin.service;

import com.bixbox.admin.domain.Exam;
import com.bixbox.admin.dto.ExamDto;
import com.bixbox.admin.exception.DuplicationEmailException;
import com.bixbox.admin.repository.ExamInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamService {
    private final ExamInfoRepository examInfoRepository;
    public Exam registerExamInfo(ExamDto examDto) {
        if (examInfoRepository.existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(examDto.getClassId(), examDto.getExamName())) {
            throw new DuplicationEmailException("ERROR100 - 중복 시험 에러");
        }
        // TODO 추가해야함
        Exam adminResult = examInfoRepository.save(examDto.convertExamDtoToExam(examDto));
        return adminResult;
    }
}
