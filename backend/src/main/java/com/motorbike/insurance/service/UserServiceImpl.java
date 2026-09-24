package com.motorbike.insurance.service;

import com.motorbike.insurance.dto.mapper.UserMapper;
import com.motorbike.insurance.dto.request.RegisterRequest;
import com.motorbike.insurance.dto.request.UpdateRequest;
import com.motorbike.insurance.dto.response.UserResponse;
import com.motorbike.insurance.entity.Role;
import com.motorbike.insurance.exception.AppException;
import com.motorbike.insurance.exception.ErrorCode;
import com.motorbike.insurance.repository.RoleRepository;
import com.motorbike.insurance.repository.UserRepository;
import com.motorbike.insurance.entity.User;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import com.motorbike.insurance.service.UserService;
import com.motorbike.insurance.dto.request.RegisterRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;

    // create user
    @Override
    public Optional<UserResponse> register(RegisterRequest request) {
       if(userRepository.existsByEmail(request.getEmail())) {
           throw new AppException(ErrorCode.EMAIL_EXISTED);
       }

       User user = userMapper.toUser(request);
       Role userRole = roleRepository.findById("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

       user.setRole(userRole);
       User savedUser = userRepository.save(user);

       if(savedUser != null) {
           UserResponse response = userMapper.toUserResponse(savedUser);
           return Optional.of(response);
       }
       return Optional.empty();
    }

    //update user info
    @Override
    public Optional<UserResponse> update(Integer userId, UpdateRequest request) {
        Optional<User> existsUser = userRepository.findById(userId);
        if(existsUser.isPresent()) {
            User user = existsUser.get();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());

            User updatedUser = userRepository.save(user);
            return Optional.of(userMapper.toUserResponse(updatedUser));
        }
        throw new AppException(ErrorCode.USER_NOT_FOUND);
    }

    // get user info by id
    @Override
    public Optional<UserResponse> getById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return Optional.of(userMapper.toUserResponse(user.get()));
        }
        throw new AppException(ErrorCode.USER_NOT_FOUND);
    }

    // get all users
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) throw new AppException(ErrorCode.USER_NOT_FOUND);

        return users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    //delete user by id
    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
