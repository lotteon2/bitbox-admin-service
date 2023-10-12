package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.dto.AdminDto;
import com.bitbox.admin.dto.AdminUpdateDto;
import com.bitbox.admin.service.AdminService;
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

    // Admin 추가 (+ 카프카 추가)
    @PostMapping("")
    public ResponseEntity<String> registerAdminInfo(@Valid @RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(adminService.registerAdminInfo(adminDto).getAdminId());
    }

    // 전체 관리자 정보 조회
    @GetMapping("")
    public ResponseEntity<List<Admin>> getAllAdminInfo(){
        return ResponseEntity.ok(adminService.getAllAdminInfo());
    }

    // 관리자 1명 정보 조회
    @GetMapping("/one")
    public ResponseEntity<Admin> getAdminInfo(@Valid @RequestHeader String adminId){
        return ResponseEntity.ok(adminService.getAdminInfo(adminId));
    }

    // 관리자 정보 수정, 삭제 (이미지, 비밀번호, 이름)
    @PatchMapping("")
    public ResponseEntity<Boolean> updateAdminInfo(@RequestHeader String adminId, @Valid @RequestBody AdminUpdateDto adminDto){
        return ResponseEntity.ok(adminService.updateAdminInfo(adminId, adminDto));
    }

//    // 교육생 권한 수정 (+ 카프카 연동 OK)
//    // TODO: 카프카
//    @PutMapping("")
//    public ResponseEntity<Admin> updateAdminRole(@Valid @RequestBody MemberAuthorityDto memberAuthorityDto){
//        return ResponseEntity.ok(adminService.updateAdminRole(memberAuthorityDto));
//    }

    // 관리자 삭제
    @DeleteMapping("")
    public ResponseEntity<Void> deleteAdmin(@Valid @RequestBody String adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok().build();
    }
}
