package com.taskmanager.task_manager.controller;

import com.taskmanager.task_manager.dto.request.LoginRequest;
import com.taskmanager.task_manager.dto.request.RefreshTokenRequest;
import com.taskmanager.task_manager.dto.request.RegisterRequest;
import com.taskmanager.task_manager.dto.request.UpdateUserRequest;
import com.taskmanager.task_manager.dto.response.AuthResponse;
import com.taskmanager.task_manager.dto.response.UserResponse;
import com.taskmanager.task_manager.response.BaseResponse;
import com.taskmanager.task_manager.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Registration, login and user lookup")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    public BaseResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return BaseResponse.created(authService.register(request), "User registered successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive a JWT")
    public BaseResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return BaseResponse.success(authService.login(request), "Login successful");
    }

    @PostMapping("/refresh")
    public BaseResponse<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthResponse response = authService.refreshToken(request);
        return BaseResponse.success(response, "Token refreshed successfully");
    }

    @PatchMapping("/users/{id}")
    public BaseResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        UserResponse response = authService.updateUser(id, request);
        return BaseResponse.success(response, "User updated successfully");
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get a user by id")
    public BaseResponse<UserResponse> getUser(@PathVariable Long id) {
        return BaseResponse.success(authService.getUserById(id));
    }
}
