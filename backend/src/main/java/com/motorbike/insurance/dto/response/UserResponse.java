package com.motorbike.insurance.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import com.motorbike.insurance.entity.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer userId;
    String firstName;
    String lastName;
    String fullName;
    String email;
    String phone;
    Role role;
    String avatarPath;
}
