package com.bixbox.admin.dto;

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
}
