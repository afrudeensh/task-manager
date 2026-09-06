package com.taskmanager.task_manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private String content;
    private Long taskId;
    private UserResponse author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}