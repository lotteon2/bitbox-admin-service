package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Exam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExamInfoRepository extends CrudRepository<Exam, Long> {
    boolean existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(Long classId, String examName);
    List<Exam> findExamByClasses_ClassIdAndDeletedIsFalse(Long classId);
}
