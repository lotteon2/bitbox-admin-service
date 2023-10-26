package com.bitbox.admin.domain.admin.service;

import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.dto.AdminDto;
import com.bitbox.admin.exception.InvalidAdminIdException;
import com.bitbox.admin.service.AdminService;
import io.github.bitbox.bitbox.enums.AuthorityType;
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
//    @BeforeEach
//    public void before(){
//        adminDto = AdminDto.builder()
//                .adminName("어드민테스트")
//                .adminEmail("test@daum.net")
//                .adminAuthority(AuthorityType.ADMIN)
//                .adminProfileImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQ61yrH-uBgiaXUvYiH8A2tMofDJrhHtdBJQ&usqp=CAU")
//                .build();
//        admin = adminService.registerAdminInfo(adminDto);
//    }

//    @DisplayName("어드민 등록 시 이미 등록된 이메일일 경우 예외가 발생해요")
//    @Order(1)
//    @Test
//    public void createAdminDuplicationEmail() throws DuplicationException {
//        assertThatThrownBy(()->adminService.registerAdminInfo(adminDto))
//                .isInstanceOf(DuplicationException.class)
//                .hasMessage("중복 어드민 이메일");
//    }
    
    @DisplayName("어드민 조회시 유효하지않은 아이디로 조회하면 예외가 발생해요")
    @Order(2)
    @Test
    void getMyInfoInvalidAdminId(){
        assertThatThrownBy(()->adminService.getAdminInfo("a", AuthorityType.ADMIN))
                .isInstanceOf(InvalidAdminIdException.class)
                .hasMessage("존재하지 않는 어드민 아이디");
    }


}
