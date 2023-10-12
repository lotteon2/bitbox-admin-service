package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Exam;
import org.springframework.data.repository.CrudRepository;

public interface ExamInfoRepository extends CrudRepository<Exam, Long> {
    boolean existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(Long classId, String examName);
}
