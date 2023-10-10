package com.bixbox.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
@Builder
@Getter
public class AdminDto {
    @NotEmpty(message = "필수 입력값입니다")
    private String adminEmail;
    @NotEmpty(message = "필수 입력값입니다")
    private String adminProfileImg;
    @NotEmpty(message = "필수 입력값입니다")
    private String adminAuthority;
    @NotEmpty(message = "필수 입력값입니다")
    private String adminName;
}
