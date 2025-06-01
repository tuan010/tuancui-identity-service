package com.tuancui.identity_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tuancui.identity_service.dto.request.UserCreationRequest;
import com.tuancui.identity_service.dto.response.UserResponse;
import com.tuancui.identity_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc// create mock request to controller
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // use for call to controller also

    @MockitoBean
    private UserService userService;

    private UserCreationRequest userCreationRequest;

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
    }


    @Test
    void createUser_validRequest_success() throws Exception {
        //GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(userCreationRequest);
        when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);

        //WHEN, THEN
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andExpect(status().isOk()).andExpect(jsonPath("code").value(1000))
                .andExpect(jsonPath("result.id").value("d2e18e00"));


    }

    @Test
    void createUser_userNameInvalid_fail() throws Exception {
        //GIVEN
        userCreationRequest.setUsername("joh");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(userCreationRequest);
//        when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);

        //WHEN, THEN
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content)).andExpect(status().isBadRequest()).andExpect(jsonPath("code").value(1003))
                .andExpect(jsonPath("message").value("Username must be at least 4 characters"));


    }

}
