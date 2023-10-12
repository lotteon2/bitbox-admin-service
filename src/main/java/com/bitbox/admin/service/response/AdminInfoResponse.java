package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoResponse {
    private String adminName;
    private String adminEmail;
    private String adminProfileImg;
    private String adminAuthority;

    public static AdminInfoResponse convertAdminToAdminInfoResponse(Admin adminInfo) {
        return AdminInfoResponse.builder().adminName(adminInfo.getAdminName())
                .adminEmail(adminInfo.getAdminEmail())
                .adminProfileImg(adminInfo.getAdminProfileImg())
                .adminAuthority(String.valueOf(adminInfo.getAdminAuthority()))
                .build();
    }
}
