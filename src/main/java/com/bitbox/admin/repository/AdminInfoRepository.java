package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminInfoRepository extends CrudRepository<Admin, String> {
    int countByAdminEmailAndDeletedIsFalse(String adminEmail);

    @Query(value = "SELECT a FROM Admin a INNER JOIN ClassAdmin c ON a.adminId = c.admin.adminId WHERE c.classes.classId = :classId AND a.deleted = false ORDER BY a.adminName")
    List<Admin> findAllByDeletedIsFalseAndClassId(Long classId);
}
