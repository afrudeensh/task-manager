package com.taskmanager.task_manager.mapper;

import com.taskmanager.task_manager.dto.request.CreateProjectRequest;
import com.taskmanager.task_manager.dto.response.ProjectResponse;
import com.taskmanager.task_manager.dto.response.UserResponse;
import com.taskmanager.task_manager.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final UserMapper userMapper;

    public Project toEntity(CreateProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        return project;
    }

    public ProjectResponse toResponse(Project project) {
        if (project == null) {
            return null;
        }

        Set<UserResponse> memberResponses = project.getMembers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toSet());

        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setOwner(userMapper.toResponse(project.getOwner()));
        response.setMembers(memberResponses);
        response.setCreatedAt(project.getCreatedAt());
        response.setUpdatedAt(project.getUpdatedAt());
        return response;
    }

    public void updateEntity(Project project, String name, String description) {
        if (name != null) {
            project.setName(name);
        }
        if (description != null) {
            project.setDescription(description);
        }
    }
}