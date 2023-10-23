package com.bitbox.admin.repository;

import com.bitbox.admin.domain.ClassAdmin;
import com.bitbox.admin.domain.key.ClassAdminId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassAdminInfoRepository extends JpaRepository<ClassAdmin, ClassAdminId> {
}
