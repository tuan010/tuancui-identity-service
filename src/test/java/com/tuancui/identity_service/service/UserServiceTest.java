package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.UserCreationRequest;
import com.tuancui.identity_service.dto.response.UserResponse;
import com.tuancui.identity_service.entity.User;
import com.tuancui.identity_service.exception.AppException;
import com.tuancui.identity_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;

    private User user;

    private UserResponse userResponse;

    private LocalDate dob;

    @BeforeEach
    private void initData(){
        dob = LocalDate.of(1990, 1, 1);

        userCreationRequest = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("d2e18e00")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();

        user = User.builder()
                .id("d2e18e00")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        //GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //WHEN
        var response =  userService.createUser(userCreationRequest);

        //THEN
        Assertions.assertThat(response.getId()).isEqualTo("d2e18e00");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");
    }

    @Test
    void createUser_userExisted_fail(){
        //GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        //WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(userCreationRequest));

        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);

        //THEN

    }

    @Test
    @WithMockUser(username = "john")
    void getMyIfo_valid_success(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        assertThat(response.getUsername()).isEqualTo("john");
        assertThat(response.getId()).isEqualTo("d2e18e00");
    }

    @Test
    @WithMockUser(username = "john")
    void getMyIfo_userNotFound_error(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        //WHEN
        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());

        assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);

    }

}
