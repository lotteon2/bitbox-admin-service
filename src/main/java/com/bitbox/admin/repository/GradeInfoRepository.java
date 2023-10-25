package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Grade;
import com.bitbox.admin.service.response.GradeByClassIdInfoResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeInfoRepository extends CrudRepository<Grade, Long> {

    @Query(value = "SELECT new com.bitbox.admin.service.response.GradeByClassIdInfoResponse(g.score, g.exam.perfectScore, g.classes.classId, e.examName, g.memberId) FROM Grade g INNER JOIN Exam e ON g.exam.examId = e.examId WHERE g.classes.classId = :classId AND g.exam.deleted = false")
    List<GradeByClassIdInfoResponse> findAllByClasses_ClassIdAndDeletedIsFalse(Long classId);
    List<Grade> findAllByMemberIdAndDeletedIsFalse(String memberId);

    @Query(value = "SELECT avg(g.score) FROM Grade g WHERE g.exam.examId = :examId GROUP BY g.exam.examId")
    Double getAvgScoreByExamId(Long examId);
}
