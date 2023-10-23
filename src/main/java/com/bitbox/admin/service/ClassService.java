package com.bitbox.admin.service;

import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.dto.ClassDto;
import com.bitbox.admin.dto.ClassInfoWithAdminList;
import com.bitbox.admin.dto.ClassUpdateDto;
import com.bitbox.admin.exception.DuplicationException;
import com.bitbox.admin.exception.InvalidClassIdException;
import com.bitbox.admin.repository.ClassInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassService {
    private final ClassInfoRepository classInfoRepository;

    @Transactional
    public Long registerClassInfo(ClassDto classDto) {
        if(classInfoRepository.existsByClassCode(classDto.getClassCode())){
            throw new DuplicationException("반 생성 | 중복 코드 에러");
        }
        if(classInfoRepository.existsByClassName(classDto.getClassName())){
            throw new DuplicationException("반 생성 | 중복 이름 에러");
        }

        return classInfoRepository.save(classDto.convertExamDtoToExam(classDto)).getClassId();
    }

    @Transactional
    public Boolean updateClassInfo(Long classId, ClassUpdateDto classUpdateDto){
        Classes classes = classInfoRepository.findById(classId).orElseThrow(()->new InvalidClassIdException("해당 클래스 아이디가 존재하지 않습니다."));
        classUpdateDto.covertClassInfoForUpdate(classes, classUpdateDto);
        return true;
    }

    public List<ClassInfoWithAdminList> getAllClassInfo(){
        List<ClassInfoWithAdminList> allClassInfoWithAdminList = classInfoRepository.findAllClassInfoWithAdminList();
        System.out.println(allClassInfoWithAdminList);
        return allClassInfoWithAdminList;
    }

}
