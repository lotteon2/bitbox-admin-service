package com.bixbox.admin.domain.key;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClassAdminId implements Serializable {
    private Long classId;
    private String adminId;
}
