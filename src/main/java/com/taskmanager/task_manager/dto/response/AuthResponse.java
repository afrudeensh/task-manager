package com.taskmanager.task_manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private String refreshToken;
    private UserResponse user;

    public AuthResponse(String token, String refreshToken, UserResponse user) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.user = user;
    }
}
