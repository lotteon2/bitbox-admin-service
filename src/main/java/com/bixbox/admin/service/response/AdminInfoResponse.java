package com.bixbox.admin.service.response;
import com.bixbox.admin.domain.Admin;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AdminInfoResponse {
    private String adminName;
    private String adminEmail;
    private String adminProfileImg;
    private String adminAuthority;

    public static AdminInfoResponse convertMemberToMemberInfoResponse(Admin adminInfo) {
        return AdminInfoResponse.builder().adminName(adminInfo.getAdminName())
                .adminEmail(adminInfo.getAdminEmail())
                .adminProfileImg(adminInfo.getAdminProfileImg())
                .adminAuthority(String.valueOf(adminInfo.getAdminAuthority()))
                .build();
    }
}
