package com.bitbox.admin.dto;

import com.bitbox.admin.domain.Admin;
import io.github.bitbox.bitbox.enums.AuthorityType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class AdminDto {
    @NotEmpty(message = "필수 입력값입니다")
    private String adminEmail;
    private AuthorityType adminAuthority;
    @NotEmpty(message = "필수 입력값입니다")
    private String adminName;
    private String adminPassword;

    public static Admin convertAdminDtoToAdmin(AdminDto adminDto) {
        return Admin.builder()
                .adminName(adminDto.getAdminName())
                .adminEmail(adminDto.getAdminEmail())
                .adminPassword(new BCryptPasswordEncoder().encode("1111"))
                .adminAuthority(adminDto.getAdminAuthority())
                .build();
    }
}
