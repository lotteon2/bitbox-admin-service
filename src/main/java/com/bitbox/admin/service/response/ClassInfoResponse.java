package com.bitbox.admin.service.response;

import com.bitbox.admin.domain.Classes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class ClassInfoResponse {
    private Long classId;
    private String className;
    private String classCode;

    public static ClassInfoResponse ClassInfoResponse(Classes classes) {
        return ClassInfoResponse.builder()
                .classId(classes.getClassId())
                .className(classes.getClassName())
                .classCode(classes.getClassCode())
                .build();
    }
}
