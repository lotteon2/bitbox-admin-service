package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Classes;
import org.springframework.data.repository.CrudRepository;

public interface ClassInfoRepository extends CrudRepository<Classes, Long> {
    boolean existsByClassNameAndClassCodeAndDeletedIsFalse(String className, String classCode);

    boolean existsByClassNameOrClassCode(String className, String classCode);

    boolean existsByClassName(String className);
    boolean existsByClassCode(String classCode);
}
