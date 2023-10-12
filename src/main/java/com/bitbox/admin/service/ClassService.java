package com.bitbox.admin.service;

import com.bitbox.admin.dto.ClassDto;
import com.bitbox.admin.exception.DuplicationException;
import com.bitbox.admin.repository.ClassInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ClassService {
    private final ClassInfoRepository classInfoRepository;

    public Long registerClassInfo(ClassDto classDto) {
        if(classInfoRepository.existsByClassCode(classDto.getClassCode())){
            throw new DuplicationException("반 생성 | 중복 코드 에러");
        }
        if(classInfoRepository.existsByClassName(classDto.getClassName())){
            throw new DuplicationException("반 생성 | 중복 이름 에러");
        }

        return classInfoRepository.save(classDto.convertExamDtoToExam(classDto)).getClassId();
    }
}
