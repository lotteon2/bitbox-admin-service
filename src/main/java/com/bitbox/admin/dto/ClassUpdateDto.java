package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Classes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClassUpdateDto {
    private String className;
    private Boolean isGraduate;
    private Boolean isDeleted;
    public Classes covertClassInfoForUpdate(Classes original, ClassUpdateDto update){
        if(update.getIsGraduate() != null) original.setGraduated(update.getIsGraduate());
        if(update.getClassName() != null) original.setClassName(update.getClassName());
        if(update.getIsDeleted() != null) original.setDeleted(update.getIsDeleted());
        return original;
    }
}
