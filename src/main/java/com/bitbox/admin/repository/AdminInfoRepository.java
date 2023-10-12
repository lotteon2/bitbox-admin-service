package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminInfoRepository extends CrudRepository<Admin, String> {
    int countByAdminEmailAndDeletedIsFalse(String adminEmail);
}
