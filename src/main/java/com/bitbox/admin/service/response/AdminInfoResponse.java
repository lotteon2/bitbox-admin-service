package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.domain.Classes;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoResponse {
    private String adminId;
    private String adminName;
    private String adminEmail;
    private String adminProfileImg;
    private AuthorityType adminAuthority;
    private List<ClassInfoResponse> classInfoResponses;

    public static AdminInfoResponse convertAdminToAdminInfoResponse(Admin adminInfo, List<Classes> classes) {
        // Admin인 계정이 본인 반 리스트 조회할 때

        List<ClassInfoResponse> result = new ArrayList<>();
        if (classes != null) {
            result = classes.stream()
                    .filter(classes1 -> !classes1.isDeleted())
                    .map(data -> ClassInfoResponse.builder()
                            .className(data.getClassName())
                            .classId(data.getClassId())
                            .classCode(data.getClassCode())
                            .exams(data.getExams().stream()
                                    .filter(exam -> !exam.isDeleted()).map(
                                            ExamResponseByClassId::from)
                                    .collect(Collectors.toList()))
                            .build())
                    .collect(Collectors.toList());
        }

        return AdminInfoResponse.builder()
                .adminName(adminInfo.getAdminName())
                .adminEmail(adminInfo.getAdminEmail())
                .adminProfileImg(adminInfo.getAdminProfileImg())
                .adminAuthority(adminInfo.getAdminAuthority())
                .adminId(adminInfo.getAdminId())
                .classInfoResponses(!result.isEmpty() ? result : adminInfo.getClassAdmins().stream().filter(admin-> !admin.getClasses().isDeleted()).map(classAdmin ->
                        ClassInfoResponse.ClassInfoResponse(classAdmin.getClasses())).collect(Collectors.toList())).build();
    }
}
