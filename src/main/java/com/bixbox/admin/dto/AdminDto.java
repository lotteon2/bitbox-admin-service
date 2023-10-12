package com.bixbox.admin.dto;

import com.bixbox.admin.domain.Admin;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class AdminDto {
    @NotEmpty(message = "필수 입력값입니다")
    private String adminEmail;
    @NotEmpty(message = "필수 입력값입니다")
    private String adminProfileImg;
    @NotNull
    private AuthorityType adminAuthority;
    @NotEmpty(message = "필수 입력값입니다")
    private String adminName;
    private String adminPassword;

    public static Admin convertAdminDtoToAdmin(AdminDto adminDto) {
        return Admin.builder()
                .adminName(adminDto.getAdminName())
                .adminEmail(adminDto.getAdminEmail())
                .adminProfileImg(adminDto.getAdminProfileImg())
                .adminAuthority(adminDto.getAdminAuthority())
                .build();
    }
}
