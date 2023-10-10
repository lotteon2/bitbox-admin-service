package com.bixbox.admin.domain;
import com.bixbox.admin.dto.AdminDto;
import com.bixbox.admin.enums.UserAuthority;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="admin")
@Entity
@DynamicInsert
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String adminId;

    @OneToMany(mappedBy = "admin")
    private List<ClassAdmin> classAdmins;

    @Column(nullable = false)
    private String adminEmail;

    @Column(nullable = false)
    @ColumnDefault("1111")
    private String adminPassword;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String adminProfileImg;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthority adminAuthority;

    @CreatedDate
    @ColumnDefault("now()")
    @Column(nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @ColumnDefault("now()")
    @Column(nullable = false,  columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ColumnDefault("false")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

    public static Admin convertAdminDtoToAdmin(AdminDto adminDto) {
        return Admin.builder()
                .adminName(adminDto.getAdminName())
                .adminEmail(adminDto.getAdminEmail())
                .adminProfileImg(adminDto.getAdminProfileImg())
                .adminAuthority(UserAuthority.valueOf(adminDto.getAdminAuthority()))
                .build();
    }
}
