package com.taskmanager.task_manager.mapper;

import com.taskmanager.task_manager.dto.request.CreateTaskRequest;
import com.taskmanager.task_manager.dto.response.TaskResponse;
import com.taskmanager.task_manager.entity.Task;
import com.taskmanager.task_manager.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserMapper userMapper;

    public Task toEntity(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setStatus(TaskStatus.TODO);
        // project, assignee, and parentTask are resolved and set by the
        // service layer, since that requires ProjectRepository/UserRepository/TaskRepository.
        return task;
    }

    public TaskResponse toResponse(Task task) {
        if (task == null) {
            return null;
        }

        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setDueDate(task.getDueDate());
        response.setProjectId(task.getProject().getId());
        response.setProjectName(task.getProject().getName());
        response.setAssignee(userMapper.toResponse(task.getAssignee()));
        response.setParentTaskId(task.getParentTask() != null ? task.getParentTask().getId() : null);
        response.setSubtaskCount(task.getSubtasks() != null ? task.getSubtasks().size() : 0);
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }

    public void updateEntity(Task task, String title, String description) {
        if (title != null) {
            task.setTitle(title);
        }
        if (description != null) {
            task.setDescription(description);
        }
        // status, priority, dueDate, and assignee are applied directly in the
        // service, since assignee requires a UserRepository lookup by ID.
    }
}