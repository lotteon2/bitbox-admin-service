package com.bixbox.admin.service;

import com.bixbox.admin.domain.Admin;
import com.bixbox.admin.dto.AdminDto;
import com.bixbox.admin.exception.DuplicationEmailException;
import com.bixbox.admin.exception.InvalidAdminIdException;
import com.bixbox.admin.repository.AdminInfoRepository;
import io.github.bitbox.bitbox.dto.MemberAuthorityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminInfoRepository adminInfoRepository;
    private final KafkaTemplate<String, MemberAuthorityDto> memberAuthorityDtoKafkaTemplate;
    @Value("${memberAuthorityTopicName}")
    private String memberAuthorityTopicName;

    @KafkaListener(topics = "${memberAuthorityTopicName}")
    public void testRegist(MemberAuthorityDto memberDto) {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(memberDto.toString());
    }

    public Admin registerAdminInfo(AdminDto adminDto) {
        if (adminInfoRepository.countByAdminEmailAndDeletedIsFalse(adminDto.getAdminEmail()) != 0) {
            throw new DuplicationEmailException("ERROR100 - 중복 이메일 에러");
        }
        Admin adminResult = adminInfoRepository.save(Admin.convertAdminDtoToAdmin(adminDto));
        memberAuthorityDtoKafkaTemplate.send(memberAuthorityTopicName, MemberAuthorityDto.builder()
                        .memberId(adminResult.getAdminId())
                        .memberAuthority(adminDto.getAdminAuthority())
                .build());
        return adminResult;
    }

    public Admin getAdminInfo(String adminId) {
        return adminInfoRepository.findById(adminId).orElseThrow(()->new InvalidAdminIdException("ERROR101 - 존재하지 않는 관리자 정보"));
    }

    public Iterable<Admin> getAllAdminInfo(){
        return adminInfoRepository.findAll();
    }
}

// service : 중간단 (비즈니스 로직)
// controller : uri 주소 처리
// repository: 쿼리문 작성 jpa가 대부분 작성해줌
// dto : 그 외에 별도로 요청, 응답 dto
// domain : jpa가 만들어주는 dto
// 도메인 만들고 백엔드 기능명세서보고 dto 만들고 레포지토리만들고 서비스 -> 컨트롤러
// -> 로직보면서 exception

