package com.motorbike.insurance.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private int code = 1000;
    private String message;
    private T result;
}
