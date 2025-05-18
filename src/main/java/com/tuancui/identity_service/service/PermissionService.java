package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.PermissionRequest;
import com.tuancui.identity_service.dto.response.PermissionResponse;
import com.tuancui.identity_service.entity.Permission;
import com.tuancui.identity_service.mapper.PermissionMapper;
import com.tuancui.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);

        Permission savedPermission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(savedPermission);
    }

    public List<PermissionResponse> getAll(){
        var lstPermission = permissionRepository.findAll();
        return lstPermission.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permissionName){
        permissionRepository.deleteById(permissionName);
    }
}
