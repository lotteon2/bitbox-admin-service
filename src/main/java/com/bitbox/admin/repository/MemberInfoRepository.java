package com.bitbox.admin.repository;

import com.bitbox.admin.domain.MemberInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MemberInfoRepository extends CrudRepository<MemberInfo, String> {
    @Query(value = "SELECT mi FROM MemberInfo mi WHERE mi.classes.classId = :classId AND mi.memberAuthority = 'TRAINEE'")
    List<MemberInfo> findByClasses_ClassId(Long classId);
}