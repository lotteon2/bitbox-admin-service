package com.bixbox.admin.repository;

import com.bixbox.admin.domain.Admin;
import com.bixbox.admin.domain.Exam;
import org.springframework.data.repository.CrudRepository;

public interface ExamInfoRepository extends CrudRepository<Exam, Long> {
    boolean existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(Long classId, String examName);
}
