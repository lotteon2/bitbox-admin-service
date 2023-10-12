package com.bixbox.admin.service;

import com.bixbox.admin.domain.Admin;
import com.bixbox.admin.dto.AdminDto;
import com.bixbox.admin.dto.AdminUpdateDto;
import com.bixbox.admin.exception.DuplicationEmailException;
import com.bixbox.admin.exception.InvalidAdminIdException;
import com.bixbox.admin.repository.AdminInfoRepository;
import io.github.bitbox.bitbox.dto.MemberAuthorityDto;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminInfoRepository adminInfoRepository;
    private final KafkaTemplate<String, MemberAuthorityDto> memberAuthorityDtoKafkaTemplate;
    @Value("${memberAuthorityTopicName}")
    private String memberAuthorityTopicName;

    public Admin registerAdminInfo(AdminDto adminDto) {
        if (adminInfoRepository.countByAdminEmailAndDeletedIsFalse(adminDto.getAdminEmail()) != 0) {
            throw new DuplicationEmailException("ERROR100 - 중복 이메일 에러");
        }
        Admin adminResult = adminInfoRepository.save(adminDto.convertAdminDtoToAdmin(adminDto));
        memberAuthorityDtoKafkaTemplate.send(memberAuthorityTopicName, MemberAuthorityDto.builder()
                        .memberId(adminResult.getAdminId())
                        .memberAuthority(adminDto.getAdminAuthority())
                .build());
        return adminResult;
    }

    @Transactional(readOnly=true)
    public Admin getAdminInfo(String adminId) {
        return adminInfoRepository.findById(adminId).orElseThrow(()->new InvalidAdminIdException("ERROR101 - 존재하지 않는 관리자 정보"));
    }

    @Transactional(readOnly=true)
    public List<Admin> getAllAdminInfo(){
        return (List<Admin>) adminInfoRepository.findAll();
    }

    @Transactional
    public Admin updateAdminInfo(String adminId, AdminUpdateDto adminUpdateDto){
        Admin admin = adminInfoRepository.findById(adminId).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        return adminUpdateDto.convertAdminInfoForUpdate(admin, adminUpdateDto);
    }

    @Transactional
    public Admin updateAdminRole(MemberAuthorityDto memberAuthorityDto){
        Admin admin = adminInfoRepository.findById(memberAuthorityDto.getMemberId()).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        admin.setAdminAuthority(memberAuthorityDto.getMemberAuthority());
        memberAuthorityDtoKafkaTemplate.send(memberAuthorityTopicName, MemberAuthorityDto.builder()
                .memberId(memberAuthorityDto.getMemberId())
                .memberAuthority(memberAuthorityDto.getMemberAuthority())
                .build());
        return admin;
    }

    @Transactional
    public void deleteAdmin(String adminId){
        Admin admin = adminInfoRepository.findById(adminId).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
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

