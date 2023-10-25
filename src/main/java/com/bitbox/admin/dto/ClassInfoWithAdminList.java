package com.bitbox.admin.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ClassInfoWithAdminList {
    private Long classId;
    private String className;
    private String classCode;
    private LocalDateTime createdAt;
    private Boolean deleted;
    private Boolean graduated;
    private List<AdminInfo> adminInfos;
}
