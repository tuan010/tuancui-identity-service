package com.tuancui.identity_service.dto.request;

import com.tuancui.identity_service.entity.Role;
import com.tuancui.identity_service.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min = 2, message = "INVALID_DOB")
    LocalDate dob;
    List<String> roles;
}
