package com.bixbox.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminUpdateDto {
    private String adminProfileImg;
    private String adminName;
    private String adminPassword;
}
