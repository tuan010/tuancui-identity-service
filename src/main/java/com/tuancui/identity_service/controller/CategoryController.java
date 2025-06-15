package com.tuancui.identity_service.controller;

import com.tuancui.identity_service.dto.response.ApiResponse;
import com.tuancui.identity_service.dto.response.CategoryResponse;
import com.tuancui.identity_service.dto.response.PublisherResponse;
import com.tuancui.identity_service.service.CategoryService;
import com.tuancui.identity_service.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> create(@RequestParam String name) {
        return ApiResponse.<CategoryResponse>builder()
                .message("bookResponse")
                .result(categoryService.create(name))
                .build();
    }
}
