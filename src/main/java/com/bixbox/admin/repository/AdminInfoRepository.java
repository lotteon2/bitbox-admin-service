package com.bixbox.admin.repository;

import com.bixbox.admin.domain.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminInfoRepository extends CrudRepository<Admin, String> {
    int countByAdminEmailAndDeletedIsFalse(String adminEmail);
}
