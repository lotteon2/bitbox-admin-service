package com.bitbox.admin.controller;

import com.bitbox.admin.domain.Admin;
import com.bitbox.admin.dto.AdminDto;
import com.bitbox.admin.dto.AdminUpdateDto;
import com.bitbox.admin.service.AdminService;
import com.bitbox.admin.service.response.AdminInfoResponse;
import io.github.bitbox.bitbox.dto.MemberAuthorityDto;
import io.github.bitbox.bitbox.enums.AuthorityType;
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

    /*
     Admin 추가 (카프카 완료)
     Admin, manager
     manager가 admin 추가하려 하면 게이트웨이에서 짜르기
     */
    @PostMapping("/{classId}")
    public ResponseEntity<String> registerAdminInfo(@Valid @RequestBody AdminDto adminDto,
                                                    @PathVariable("classId") Long classId) {
        return ResponseEntity.ok(adminService.registerAdminInfo(adminDto, classId).getAdminId());
    }

    /*
    * 전체 관리자 정보 조회
    * Admin, manager
    * */
    @GetMapping("/{classId}")
    public ResponseEntity<List<AdminInfoResponse>> getAllAdminInfoByClassId(@Valid @PathVariable("classId") Long classId){
        return ResponseEntity.ok(adminService.getAllAdminInfo(classId));
    }

    /*
     관리자 1명 정보 조회
     Admin, manager, teacher
     */

    /**
     *
     * @param memberId
     * @param memberAuthority
     * @return
     */
    @GetMapping("/one")
    public ResponseEntity<AdminInfoResponse> getAdminInfo(@RequestHeader String memberId, @RequestHeader AuthorityType memberAuthority){
        return ResponseEntity.ok(adminService.getAdminInfo(memberId, memberAuthority));
    }

    /* 관리자 정보 수정, 삭제 (이미지, 비밀번호, 이름)
    Admin, manager
    manager가 admin바꾸려고 하면 gateway에서 자르기
     */
    @PatchMapping("/{adminId}")
    public ResponseEntity<Boolean> updateAdminInfo(@PathVariable String adminId, @Valid @RequestBody AdminUpdateDto adminDto){
        return ResponseEntity.ok(adminService.updateAdminInfo(adminId, adminDto));
    }

    /**
     * 내 정보 수정(헤더 필요)
     * @param adminId
     * @param adminDto
     * @return
     * admin, manager, teacher
     */
    @PatchMapping("")
    public ResponseEntity<Boolean> updateMyAdminInfo(@RequestHeader String memberId, @Valid @RequestBody AdminUpdateDto adminDto){
        return ResponseEntity.ok(adminService.updateAdminInfo(memberId, adminDto));
    }

    /* 교육생 권한 수정 (+ 카프카 연동 OK)
    * Admin, manager
     */
    @PutMapping("")
    public ResponseEntity<Admin> updateAdminRole(@Valid @RequestBody MemberAuthorityDto memberAuthorityDto){
        return ResponseEntity.ok(adminService.updateAdminRole(memberAuthorityDto));
    }
}
