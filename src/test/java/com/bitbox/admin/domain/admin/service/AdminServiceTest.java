package com.bitbox.admin.domain.admin.service;

import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.dto.AdminDto;
import com.bitbox.admin.exception.DuplicationEmailException;
import com.bitbox.admin.service.AdminService;
import io.github.bitbox.bitbox.enums.AuthorityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;
    private AdminDto adminDto;
    private Admin admin;
    @BeforeEach
    public void before(){
        adminDto = AdminDto.builder()
                .adminName("어드민테스트")
                .adminEmail("test@daum.net")
                .adminAuthority(AuthorityType.ADMIN)
                .adminProfileImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQ61yrH-uBgiaXUvYiH8A2tMofDJrhHtdBJQ&usqp=CAU")
                .build();
        admin = adminService.registerAdminInfo(adminDto);
    }

    @DisplayName("어드민 등록 시 이미 등록된 이메일일 경우 예외가 발생해요")
    @Order(1)
    @Test
    public void createAdminDuplicationEmail() throws DuplicationEmailException {
        assertThatThrownBy(()->adminService.registerAdminInfo(adminDto))
                .isInstanceOf(DuplicationEmailException.class)
                .hasMessage("ERROR100 - 중복 이메일 에러");
    }


}
