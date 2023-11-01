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
import io.github.bitbox.bitbox.dto.AdminBoardRegisterDto;
import io.github.bitbox.bitbox.dto.AdminMemberBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final KafkaTemplate<String, AdminBoardRegisterDto> kafkaTemplate1;
    private final KafkaTemplate<String, AdminMemberBoardDto> kafkaTemplate2;

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

        kafkaTemplate1.send(
                "adminBoardCreateTopic",
                AdminBoardRegisterDto.builder().classId(classes.getClassId()).classCode(classes.getClassCode()).build()
        );
        return classes.getClassId();
    }

    @Transactional
    public Boolean updateClassInfo(Long classId, ClassUpdateDto classUpdateDto){
        Classes classes = classInfoRepository.findById(classId).orElseThrow(()->new InvalidClassIdException("해당 클래스 아이디가 존재하지 않습니다."));
        Classes updatedClasses =  classUpdateDto.covertClassInfoForUpdate(classes, classUpdateDto);
        if(updatedClasses.isDeleted()) {
            kafkaTemplate2.send("adminMemberBoardTopic", AdminMemberBoardDto.builder().classId(classId).requestDate(LocalDateTime.now()).build());
            kafkaTemplate2.send("adminBoardDeleteTopic", AdminMemberBoardDto.builder().classId(classId).requestDate(LocalDateTime.now()).build());
        }
        return true;
    }

    public List<ClassInfoWithAdminList> getAllClassInfo(){
        List<ClassInfoWithAdminList> allClassInfoWithAdminList = classInfoRepository.findAllClassInfoWithAdminList();
        System.out.println(allClassInfoWithAdminList.toString());
        return allClassInfoWithAdminList;
    }

}
