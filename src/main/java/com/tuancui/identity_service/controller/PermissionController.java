package com.tuancui.identity_service.controller;

import com.tuancui.identity_service.dto.request.PermissionRequest;
import com.tuancui.identity_service.dto.response.ApiResponse;
import com.tuancui.identity_service.dto.response.PermissionResponse;
import com.tuancui.identity_service.entity.Permission;
import com.tuancui.identity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {

    PermissionService permissionService;

    @PostMapping()
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping()
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<Void> delete(@PathVariable String permissionId){
        permissionService.delete(permissionId);
        return ApiResponse.<Void>builder().build();
    }
}
