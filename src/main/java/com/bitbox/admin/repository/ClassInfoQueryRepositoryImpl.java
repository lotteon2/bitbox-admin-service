package com.bitbox.admin.repository;

import com.bitbox.admin.dto.AdminInfo;
import com.bitbox.admin.dto.ClassInfoWithAdminList;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bitbox.admin.domain.QAdmin.admin;
import static com.bitbox.admin.domain.QClassAdmin.classAdmin;
import static com.bitbox.admin.domain.QClasses.classes;

public class ClassInfoQueryRepositoryImpl implements ClassInfoQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public ClassInfoQueryRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ClassInfoWithAdminList> findAllClassInfoWithAdminList() {
        return jpaQueryFactory
                .from(classes)
                .innerJoin(classAdmin).on(classAdmin.classes.classId.eq(classes.classId))
                .where(classes.deleted.isFalse())
                .transform(groupBy(classes.classId).list(Projections.constructor(ClassInfoWithAdminList.class,
                        classes.classId,
                        classes.className,
                        classes.classCode,
                        classes.createdAt,
                        classes.deleted,
                        classes.graduated,
                        list(Projections.constructor(AdminInfo.class,
                        classAdmin.admin.adminEmail,
                        classAdmin.admin.adminName,
                        classAdmin.admin.adminProfileImg)))));
    }
}
