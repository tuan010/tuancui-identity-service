package com.tuancui.identity_service.mapper;

import com.tuancui.identity_service.dto.request.UserCreationRequest;
import com.tuancui.identity_service.dto.request.UserUpdateRequest;
import com.tuancui.identity_service.dto.response.UserResponse;
import com.tuancui.identity_service.entity.User;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);


    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);

    UserCreationRequest toUserCreationRequest(User user, String username);
}
