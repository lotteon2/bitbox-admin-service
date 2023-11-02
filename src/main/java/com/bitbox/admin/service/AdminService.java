package com.bitbox.admin.service;

import com.bitbox.admin.domain.ClassAdmin;
import com.bitbox.admin.domain.Classes;
import com.bitbox.admin.domain.key.ClassAdminId;
import com.bitbox.admin.dto.AdminDto;
import com.bitbox.admin.dto.AdminUpdateDto;
import com.bitbox.admin.exception.DuplicationException;
import com.bitbox.admin.exception.InvalidAdminIdException;
import com.bitbox.admin.repository.AdminInfoRepository;
import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.repository.ClassAdminInfoRepository;
import com.bitbox.admin.repository.ClassInfoRepository;
import com.bitbox.admin.service.response.AdminInfoResponse;
import io.github.bitbox.bitbox.dto.MemberAuthorityDto;
import io.github.bitbox.bitbox.dto.MemberRegisterDto;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final AdminInfoRepository adminInfoRepository;
    private final ClassInfoRepository classInfoRepository;
    private final ClassAdminInfoRepository classAdminInfoRepository;
    private final KafkaTemplate<String, MemberRegisterDto> memberRegisterDtoKafkaTemplate;
    private final KafkaTemplate<String, MemberAuthorityDto> memberAuthorityDtoKafkaTemplate;
    private String INVALID_ADMIN_EXCEPTION = "존재하지 않는 관리자 정보";
    @Value("${memberAuthorityTopicName}")
    private String memberAuthorityTopicName;

    @Value("${memberRegisterTopic}")
    private String memberRegisterTopic;

    @Transactional
    public Admin registerAdminInfo(AdminDto adminDto, Long classId) {
        if (adminInfoRepository.countByAdminEmailAndDeletedIsFalse(adminDto.getAdminEmail()) != 0) {
            throw new DuplicationException("ERROR100 - 중복 이메일 에러");
        }
        Admin admin = adminInfoRepository.save(adminDto.convertAdminDtoToAdmin(adminDto));
        Classes classes = classInfoRepository.findById(classId).orElseThrow(() -> new InvalidAdminIdException(INVALID_ADMIN_EXCEPTION));

        ClassAdminId classAdminId = new ClassAdminId(classId, admin.getAdminId());
        ClassAdmin classAdmin = new ClassAdmin(classAdminId, classes, admin);
        classAdminInfoRepository.save(classAdmin);

        memberRegisterDtoKafkaTemplate.send(memberRegisterTopic, MemberRegisterDto.builder()
                        .id(admin.getAdminId())
                        .classId(null)
                        .authority(adminDto.getAdminAuthority())
                        .profileImg("")
                        .email(admin.getAdminEmail())
                        .name(admin.getAdminName())
                        .build());


        return admin;
    }

    public AdminInfoResponse getAdminInfo(String adminId, AuthorityType authorityType) {
        Admin admin = adminInfoRepository.findById(adminId).orElseThrow(() -> new InvalidAdminIdException(INVALID_ADMIN_EXCEPTION));
        List<Classes> classes;
        if(authorityType == AuthorityType.ADMIN){
            classes = classInfoRepository.findAllByDeletedIsFalse();
            return AdminInfoResponse.convertAdminToAdminInfoResponse(admin, classes);
        }
        return AdminInfoResponse.convertAdminToAdminInfoResponse(admin, null);
    }

    public List<AdminInfoResponse> getAllAdminInfo(Long classId){
        List<Admin> admins = adminInfoRepository.findAllByDeletedIsFalseAndClassId(classId);
        List<AdminInfoResponse> adminResults = new ArrayList();

        for(Admin admin: admins){
            adminResults.add(AdminInfoResponse.convertAdminToAdminInfoResponse(admin, null));
        }

        return adminResults;
    }

    @Transactional
    public boolean updateAdminInfo(String adminId, AdminUpdateDto adminUpdateDto){
        Admin admin = adminInfoRepository.findById(adminId).orElseThrow(() -> new InvalidAdminIdException(INVALID_ADMIN_EXCEPTION));
        adminUpdateDto.convertAdminInfoForUpdate(admin, adminUpdateDto);
        return true;
    }

    @Transactional
    public Admin updateAdminRole(MemberAuthorityDto memberAuthorityDto){
        Admin admin = adminInfoRepository.findById(memberAuthorityDto.getMemberId()).orElseThrow(() -> new InvalidAdminIdException(INVALID_ADMIN_EXCEPTION));
        admin.setAdminAuthority(memberAuthorityDto.getMemberAuthority());
        memberAuthorityDtoKafkaTemplate.send(memberAuthorityTopicName, MemberAuthorityDto.builder()
                .memberId(memberAuthorityDto.getMemberId())
                .memberAuthority(memberAuthorityDto.getMemberAuthority())
                .build());
        return admin;
    }

    @Transactional
    public void deleteAdmin(String adminId){
        Admin admin = adminInfoRepository.findById(adminId).orElseThrow(() -> new InvalidAdminIdException(INVALID_ADMIN_EXCEPTION));
        admin.setDeleted(true);
        memberAuthorityDtoKafkaTemplate.send(memberAuthorityTopicName, MemberAuthorityDto.builder()
                .memberId(adminId)
                .memberAuthority(AuthorityType.GENERAL)
                .build());
    }
}

// service : 중간단 (비즈니스 로직)
// controller : uri 주소 처리
// repository: 쿼리문 작성 jpa가 대부분 작성해줌
// dto : 그 외에 별도로 요청, 응답 dto
// domain : jpa가 만들어주는 dto
// 도메인 만들고 백엔드 기능명세서보고 dto 만들고 레포지토리만들고 서비스 -> 컨트롤러
// -> 로직보면서 exception

