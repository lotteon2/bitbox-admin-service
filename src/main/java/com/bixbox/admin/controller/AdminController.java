package com.bixbox.admin.controller;

import com.bixbox.admin.domain.Admin;
import com.bixbox.admin.dto.AdminDto;
import com.bixbox.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Admin 추가
    @PostMapping("")
    public ResponseEntity<String> registerAdminInfo(@Valid @RequestBody AdminDto adminDto) {
        System.out.println(adminDto.getAdminAuthority());
        return ResponseEntity.ok(adminService.registerAdminInfo(adminDto).getAdminId());
    }

    // 전체 관리자 정보 조회
    @GetMapping("")
    public ResponseEntity<Iterable<Admin>> getAllAdminInfo(){
        return ResponseEntity.ok(adminService.getAllAdminInfo());
    }

    // 관리자 1명 정보 조회
    @GetMapping("/one")
    public ResponseEntity<Admin> getAdminInfo(@Valid @RequestHeader String adminId){
        return ResponseEntity.ok(adminService.getAdminInfo(adminId));
    }

}
