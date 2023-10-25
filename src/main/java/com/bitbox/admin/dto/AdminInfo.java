package com.bitbox.admin.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AdminInfo {
    private String adminEmail;
    private String adminName;
    private String adminProfileImg;

}
