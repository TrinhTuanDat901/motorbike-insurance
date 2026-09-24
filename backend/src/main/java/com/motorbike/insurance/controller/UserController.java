package com.motorbike.insurance.controller;

import com.motorbike.insurance.dto.request.RegisterRequest;
import com.motorbike.insurance.dto.request.UpdateRequest;
import com.motorbike.insurance.dto.response.ApiResponse;
import com.motorbike.insurance.dto.response.UserResponse;
import com.motorbike.insurance.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/register")
    public ApiResponse<Optional<UserResponse>> registerUser(@RequestBody RegisterRequest request) {
        ApiResponse<Optional<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.register(request));
        response.setMessage("User registered successfully");
        return response;
    }

    @PostMapping("/update/{userId}")
    public ApiResponse<Optional<UserResponse>> updateUser(@PathVariable Integer userId, @RequestBody UpdateRequest request) {
        ApiResponse<Optional<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.update(userId, request));
        response.setMessage("User updated successfully");
        return response;
    }

    @GetMapping("/{userId}")
    public ApiResponse<Optional<UserResponse>> getUserById(@PathVariable Integer userId) {
        ApiResponse<Optional<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getById(userId));
        response.setMessage("User retrieved successfully");
        return response;
    }

    @GetMapping("/all")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getAllUsers());
        response.setMessage("Users retrieved successfully");
        return response;
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Integer userId) {
        ApiResponse<Void> response = new ApiResponse<>();
        userService.deleteUser(userId);
        response.setMessage("User deleted successfully");
        return response;
    }
}
