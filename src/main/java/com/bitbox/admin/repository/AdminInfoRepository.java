package com.bitbox.admin.repository;

import com.bitbox.admin.domain.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminInfoRepository extends CrudRepository<Admin, String> {
    int countByAdminEmailAndDeletedIsFalse(String adminEmail);
    List<Admin> findAllByDeletedIsFalse();
}
