package com.motorbike.insurance.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    INVALID_KEY(1001, "Invalid key - Unknown error code", HttpStatus.BAD_REQUEST),
    DATA_ACCESS_EXCEPTION(9998, "Data access exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    USER_EXISTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email already exists", HttpStatus.BAD_REQUEST),
    USER_CREATION_FAILED (1004, "Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_NOT_EXISTED(1006, "Email does not exist", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(1009, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1010, "You are not permission to access this resource", HttpStatus.FORBIDDEN),

    ROLE_NOT_FOUND(1013, "Role not found", HttpStatus.NOT_FOUND),
    ROLE_CREATION_FAILED(1014, "Failed to create role", HttpStatus.INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

