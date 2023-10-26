package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Exam;
import com.bitbox.admin.service.response.ExamInfoResponse;
import com.bitbox.admin.service.response.GradeInfoResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamInfoRepository extends CrudRepository<Exam, Long> {
    boolean existsByClasses_ClassIdAndExamNameAndDeletedIsFalse(Long classId, String examName);

    @Query(value = "SELECT new com.bitbox.admin.service.response.ExamInfoResponse(e.examId, e.examName, c.classId, c.className, e.perfectScore, avg(g.score)) FROM Exam e INNER JOIN Grade g ON e.examId = g.exam.examId INNER JOIN Classes c ON e.classes.classId = c.classId WHERE e.classes.classId = :classId AND e.deleted = false GROUP BY e.examId")
    List<ExamInfoResponse> findExamByClasses_ClassIdAndDeletedIsFalse(Long classId);
}
