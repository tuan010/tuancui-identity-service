package com.tuancui.identity_service.mapper;

import com.tuancui.identity_service.dto.request.PermissionRequest;
import com.tuancui.identity_service.dto.request.RoleRequest;
import com.tuancui.identity_service.dto.response.PermissionResponse;
import com.tuancui.identity_service.dto.response.RoleResponse;
import com.tuancui.identity_service.entity.Permission;
import com.tuancui.identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role permission);

}
