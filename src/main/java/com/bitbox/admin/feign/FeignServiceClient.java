package com.bitbox.admin.feign;

import io.github.bitbox.bitbox.dto.MemberTraineeResult;
import io.github.bitbox.bitbox.dto.MemberValidDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-service")
public interface FeignServiceClient {
    @PostMapping(value="/user-service/member/admin/check")
    ResponseEntity<MemberTraineeResult> getMemberIsValidToAddExam(@RequestBody List<MemberValidDto> memberValidDto);

}