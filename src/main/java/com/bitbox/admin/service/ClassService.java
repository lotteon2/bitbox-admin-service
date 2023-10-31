package com.bitbox.admin.service;

import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.domain.ClassAdmin;
import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.key.ClassAdminId;
import com.bitbox.admin.dto.ClassDto;
import com.bitbox.admin.dto.ClassInfoWithAdminList;
import com.bitbox.admin.dto.ClassUpdateDto;
import com.bitbox.admin.exception.DuplicationException;
import com.bitbox.admin.exception.InvalidClassIdException;
import com.bitbox.admin.repository.AdminInfoRepository;
import com.bitbox.admin.repository.ClassAdminInfoRepository;
import com.bitbox.admin.repository.ClassInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassService {
    private final ClassInfoRepository classInfoRepository;
    private final ClassAdminInfoRepository classAdminInfoRepository;
    private final AdminInfoRepository adminInfoRepository;

    @Transactional
    public Long registerClassInfo(String adminId, ClassDto classDto) {
        if(classInfoRepository.existsByClassCode(classDto.getClassCode())){
            throw new DuplicationException("반 생성 | 중복 코드 에러");
        }
        if(classInfoRepository.existsByClassName(classDto.getClassName())){
            throw new DuplicationException("반 생성 | 중복 이름 에러");
        }

        Admin admin = adminInfoRepository.findById(adminId).orElseThrow(()->new RuntimeException("exception"));
        Classes classes = classInfoRepository.save(classDto.convertExamDtoToExam(classDto));
        ClassAdminId classAdminId = new ClassAdminId(classes.getClassId(), adminId);
        ClassAdmin classAdmin = new ClassAdmin(classAdminId, classes, admin);
        classAdminInfoRepository.save(classAdmin);

        return classes.getClassId();
    }

    @Transactional
    public Boolean updateClassInfo(Long classId, ClassUpdateDto classUpdateDto){
        Classes classes = classInfoRepository.findById(classId).orElseThrow(()->new InvalidClassIdException("해당 클래스 아이디가 존재하지 않습니다."));
        classUpdateDto.covertClassInfoForUpdate(classes, classUpdateDto);
        return true;
    }

    public List<ClassInfoWithAdminList> getAllClassInfo(){
        List<ClassInfoWithAdminList> allClassInfoWithAdminList = classInfoRepository.findAllClassInfoWithAdminList();
        System.out.println(allClassInfoWithAdminList.toString());
        return allClassInfoWithAdminList;
    }

}
