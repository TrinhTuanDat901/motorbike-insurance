package com.motorbike.insurance.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String firstName;
    String lastName;
    String email;
    String password = "123456";
    String phone;
    String avatarPath = null;
}
