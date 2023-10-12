package com.bixbox.admin.dto;

import com.bixbox.admin.domain.Admin;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminUpdateDto {
    private String adminProfileImg;
    private String adminName;
    private String adminPassword;

    public static Admin convertAdminInfoForUpdate(Admin original, AdminUpdateDto update) {
        if(update.getAdminPassword() != null) original.setAdminPassword(update.getAdminPassword());
        if(update.getAdminProfileImg() != null) original.setAdminProfileImg(update.getAdminProfileImg());
        if(update.getAdminName() != null) original.setAdminName(update.getAdminName());
        return original;
    }
}
