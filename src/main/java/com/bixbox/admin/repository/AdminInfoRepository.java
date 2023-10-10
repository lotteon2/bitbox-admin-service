package com.bixbox.admin.repository;

import com.bixbox.admin.domain.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminInfoRepository extends CrudRepository<Admin, String> {
    int countByAdminEmailAndDeletedIsFalse(String adminEmail);
}
