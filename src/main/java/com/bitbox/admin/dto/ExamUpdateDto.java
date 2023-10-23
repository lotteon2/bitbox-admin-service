package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Exam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExamUpdateDto {
//    @NotNull(message = "필수 입력값입니다")
    private String examName;
    private Long perfectScore;
    private Boolean isDeleted;

    public static Exam convertExamInfoForUpdate(Exam original, ExamUpdateDto update){
        if(update.getIsDeleted() != null) original.setDeleted(update.getIsDeleted());
        if(update.getExamName() != null) original.setExamName(update.getExamName());
        if(update.getPerfectScore() != null) original.setPerfectScore(update.getPerfectScore());
        return original;
    }
}
