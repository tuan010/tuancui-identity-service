package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.PermissionRequest;
import com.tuancui.identity_service.dto.request.RoleRequest;
import com.tuancui.identity_service.dto.response.PermissionResponse;
import com.tuancui.identity_service.dto.response.RoleResponse;
import com.tuancui.identity_service.entity.Permission;
import com.tuancui.identity_service.mapper.PermissionMapper;
import com.tuancui.identity_service.mapper.RoleMapper;
import com.tuancui.identity_service.repository.PermissionRepository;
import com.tuancui.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll(){
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String roleName){
        roleRepository.deleteById(roleName);
    }
}
