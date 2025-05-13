package com.tuancui.identity_service.controller;

import com.tuancui.identity_service.dto.request.UserCreationRequest;
import com.tuancui.identity_service.dto.request.UserUpdateRequest;
import com.tuancui.identity_service.dto.response.ApiResponse;
import com.tuancui.identity_service.dto.response.UserResponse;
import com.tuancui.identity_service.entity.User;
import com.tuancui.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping()
    private ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;
    }

    @GetMapping()
    private ApiResponse<List<UserResponse>> getUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    private UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    private UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest updateRequest) {
        return userService.updateUser(userId, updateRequest);
    }

    @DeleteMapping("/{userId}")
    private String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

}
