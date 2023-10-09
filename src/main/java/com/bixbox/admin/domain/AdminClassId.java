package com.bixbox.admin.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

// admin, class 복합키
@Embeddable
public class AdminClassId implements Serializable {
    private Long adminId;
    private String classId;
}
