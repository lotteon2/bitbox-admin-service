package com.bitbox.admin.repository;

import com.bitbox.admin.domain.MemberInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MemberInfoRepository extends CrudRepository<MemberInfo, String> {
    List<MemberInfo> findByClasses_ClassId(Long classId);
}