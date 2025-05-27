package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.UserCreationRequest;
import com.tuancui.identity_service.dto.request.UserUpdateRequest;
import com.tuancui.identity_service.dto.response.UserResponse;
import com.tuancui.identity_service.entity.User;
import com.tuancui.identity_service.enums.Role;
import com.tuancui.identity_service.exception.AppException;
import com.tuancui.identity_service.exception.ErrorCode;
import com.tuancui.identity_service.mapper.UserMapper;
import com.tuancui.identity_service.repository.RoleRepository;
import com.tuancui.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleResult;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request){
        log.info("UserService: create User");
        if(userRepository.existsByUsername(request.getUsername())){
            log.info("throw exception here");
          throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers(){
        log.info("In method get users");
        List<User> userList =  userRepository.findAll();
        return userList.stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    public UserResponse getMyInfo(){
        var context =  SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user =  userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(existingUser, request);
        existingUser.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        existingUser.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(existingUser));
    }


    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String id){
        log.info("In method get user by Id");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }


    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
