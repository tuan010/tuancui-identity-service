package com.tuancui.identity_service.controller;

import com.tuancui.identity_service.dto.response.ApiResponse;
import com.tuancui.identity_service.dto.response.PublisherResponse;
import com.tuancui.identity_service.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ApiResponse<PublisherResponse> create(@RequestParam String name) {
        return ApiResponse.<PublisherResponse>builder()
                .message("bookResponse")
                .result(publisherService.create(name))
                .build();
    }
}
