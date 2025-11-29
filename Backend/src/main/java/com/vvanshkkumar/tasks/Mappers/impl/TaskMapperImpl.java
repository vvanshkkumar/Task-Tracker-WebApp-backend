package com.vvanshkkumar.tasks.Mappers.impl;

import com.vvanshkkumar.tasks.domain.Dto.TaskDto;
import com.vvanshkkumar.tasks.Mappers.TaskMapper;
import com.vvanshkkumar.tasks.domain.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.taskStatus(),
                taskDto.taskPriority(),
                null,
                null,
                null);
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getTaskStatus()
                );
    }
}
