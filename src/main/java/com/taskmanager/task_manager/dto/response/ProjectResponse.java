package com.taskmanager.task_manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private UserResponse owner;
    private Set<UserResponse> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}