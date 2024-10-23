package com.module.api.sample;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.module.response.RestResponse.RestResultResponse;

@RequestMapping("/api/v1")
@Tag(name = "Sample", description = "샘플 입니다.")
@RestController
@RequiredArgsConstructor
public class SampleController {

    @ApiOperation(tags = "Sample", value = "샘플 목록 조회", notes = "샘플 목록을 조회합니다.")
    @GetMapping(value = "/samples-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResultResponse> getSamplesList(){

        return ResponseEntity.ok(RestResultResponse.builder().build());
    }
}
