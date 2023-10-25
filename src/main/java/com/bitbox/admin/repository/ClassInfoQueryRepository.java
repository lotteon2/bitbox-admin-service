package com.bitbox.admin.repository;

import com.bitbox.admin.dto.ClassInfoWithAdminList;

import java.util.List;

public interface ClassInfoQueryRepository {
List<ClassInfoWithAdminList> findAllClassInfoWithAdminList();
}
