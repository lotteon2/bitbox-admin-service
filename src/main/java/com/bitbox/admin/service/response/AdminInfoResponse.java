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
    private String adminName;
    private String adminEmail;
    private String adminProfileImg;
    private AuthorityType adminAuthority;
    private List<ClassInfoResponse> classInfoResponses;

    public static AdminInfoResponse convertAdminToAdminInfoResponse(Admin adminInfo, List<Classes> classes) {
        // Admin인 계정이 본인 반 리스트 조회할 때
        if(classes != null) {
            // classes -> classInfoResponses
            List<ClassInfoResponse> result = new ArrayList<>();
            for (Classes classe : classes) {
                result.add(ClassInfoResponse.builder()
                        .className(classe.getClassName())
                        .classId(classe.getClassId())
                        .classCode(classe.getClassCode())
                        .build());
            }
            return AdminInfoResponse.builder().adminName(adminInfo.getAdminName())
                    .adminEmail(adminInfo.getAdminEmail())
                    .adminProfileImg(adminInfo.getAdminProfileImg())
                    .adminAuthority(adminInfo.getAdminAuthority())
                    .classInfoResponses(result)
                    .build();
        };
        return AdminInfoResponse.builder().adminName(adminInfo.getAdminName())
                .adminEmail(adminInfo.getAdminEmail())
                .adminProfileImg(adminInfo.getAdminProfileImg())
                .adminAuthority(adminInfo.getAdminAuthority())
                .classInfoResponses(adminInfo.getClassAdmins().stream().map(classAdmin ->
                        ClassInfoResponse.ClassInfoResponse(classAdmin.getClasses())
                ).collect(Collectors.toList()))
                .build();
    }
}
