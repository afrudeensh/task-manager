package com.taskmanager.task_manager.mapper;

import com.taskmanager.task_manager.dto.request.CreateCommentRequest;
import com.taskmanager.task_manager.dto.response.CommentResponse;
import com.taskmanager.task_manager.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;

    public Comment toEntity(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        // task and author are resolved and set by the service layer.
        return comment;
    }

    public CommentResponse toResponse(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
        response.setTaskId(comment.getTask().getId());
        response.setAuthor(userMapper.toResponse(comment.getAuthor()));
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        return response;
    }

    public void updateEntity(Comment comment, String content) {
        if (content != null) {
            comment.setContent(content);
        }
    }
}