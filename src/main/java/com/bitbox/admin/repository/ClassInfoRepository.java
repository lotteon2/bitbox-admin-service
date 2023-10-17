package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Classes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassInfoRepository extends CrudRepository<Classes, Long> {
    boolean existsByClassName(String className);
    boolean existsByClassCode(String classCode);
    List<Classes> findAllByDeletedIsFalse();
    List<Classes> findByClassIdAndDeletedIsFalse(Long classId);
}
