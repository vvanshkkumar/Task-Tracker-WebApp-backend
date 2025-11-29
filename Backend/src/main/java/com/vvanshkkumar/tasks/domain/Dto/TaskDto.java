package com.vvanshkkumar.tasks.domain.Dto;

import com.vvanshkkumar.tasks.domain.entities.TaskPriority;
import com.vvanshkkumar.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus
) {

}
