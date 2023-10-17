package com.bitbox.admin.domain.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ClassAdminId implements Serializable {

    @Column(name = "class_id")
    private Long classId;
    @Column(name = "admin_id")
    private String adminId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassAdminId classAdminId = (ClassAdminId) o;
        return Objects.equals(getClassId(), classAdminId.getClassId()) && Objects.equals(getAdminId(), classAdminId.getAdminId());

    }
    @Override
    public int hashCode() {
        return Objects.hash(getClassId(), getAdminId());
    }
}
