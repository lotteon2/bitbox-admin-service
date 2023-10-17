package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeInfoRepository extends CrudRepository<Grade, Long> {
    List<Grade> findAllByClasses_ClassIdAndDeletedIsFalse(Long classId);
    List<Grade> findAllByMemberIdAndDeletedIsFalse(String memberId);
}
