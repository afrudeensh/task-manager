package com.taskmanager.task_manager.mapper;

import com.taskmanager.task_manager.dto.request.RegisterRequest;
import com.taskmanager.task_manager.dto.response.UserResponse;
import com.taskmanager.task_manager.entity.User;
import com.taskmanager.task_manager.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        // password is set separately in the service after BCrypt encoding —
        // the mapper never handles raw or encoded passwords.
        user.setRole(UserRole.USER);
        return user;
    }

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}