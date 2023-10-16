package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Admin;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoResponse {
    private String adminName;
    private String adminEmail;
    private String adminProfileImg;
    private AuthorityType adminAuthority;

    public static AdminInfoResponse convertAdminToAdminInfoResponse(Admin adminInfo) {
        return AdminInfoResponse.builder().adminName(adminInfo.getAdminName())
                .adminEmail(adminInfo.getAdminEmail())
                .adminProfileImg(adminInfo.getAdminProfileImg())
                .adminAuthority(adminInfo.getAdminAuthority())
                .build();
    }
}
