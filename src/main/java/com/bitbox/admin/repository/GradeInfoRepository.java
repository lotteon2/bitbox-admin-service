package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.service.response.GradeByClassIdInfoResponse;
import com.bitbox.admin.service.response.GradeByExamIdInfoResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeInfoRepository extends CrudRepository<Grade, Long> {

    @Query(value = "SELECT new com.bitbox.admin.service.response.GradeByClassIdInfoResponse(g.gradeId, avg(g.score), g.exam.perfectScore, g.classes.classId, e.examName, g.memberId) FROM Grade g INNER JOIN Exam e ON g.exam.examId = e.examId WHERE g.classes.classId = :classId AND g.exam.deleted = false GROUP BY g.exam.examId")
    List<GradeByClassIdInfoResponse> findAllByClasses_ClassIdAndDeletedIsFalse(Long classId);
    @Query(value = "SELECT new com.bitbox.admin.service.response.GradeByExamIdInfoResponse(g.gradeId, g.memberId, e.examName, e.examId, e.perfectScore) FROM Grade g INNER JOIN Exam e ON g.exam.examId = e.examId WHERE e.examId = :examId AND e.deleted = false")
    List<GradeByExamIdInfoResponse> findAllByExam_ExamIdAndDeletedIsFalse(Long examId);
    List<Grade> findAllByMemberIdAndDeletedIsFalse(String memberId);

    @Query(value = "SELECT avg(g.score) FROM Grade g WHERE g.exam.examId = :examId GROUP BY g.exam.examId")
    Double getAvgScoreByExamId(Long examId);
}
