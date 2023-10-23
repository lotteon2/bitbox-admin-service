package com.bitbox.admin.feign;

import io.github.bitbox.bitbox.dto.MemberTraineeResult;
import io.github.bitbox.bitbox.dto.MemberValidDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-service")
@FeignClient(name = "feignClient",
        url = "https://bitbox.kro.kr/")
public interface FeignServiceClient {
    @PostMapping(value="/member/admin/check")
    ResponseEntity<MemberTraineeResult> getMemberIsValidToAddExam(@RequestBody List<MemberValidDto> memberValidDto);

}