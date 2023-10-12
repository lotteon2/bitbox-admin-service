package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Admin;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminUpdateDto {
    private String adminProfileImg;
    private String adminName;
    private String adminPassword;
    private Boolean isDeleted;

    public static Admin convertAdminInfoForUpdate(Admin original, AdminUpdateDto update) {
        if(update.getAdminPassword() != null) original.setAdminPassword(update.getAdminPassword());
        if(update.getAdminProfileImg() != null) original.setAdminProfileImg(update.getAdminProfileImg());
        if(update.getAdminName() != null) original.setAdminName(update.getAdminName());
        if(update.getIsDeleted() != null) original.setDeleted(update.getIsDeleted());
        return original;
    }
}
