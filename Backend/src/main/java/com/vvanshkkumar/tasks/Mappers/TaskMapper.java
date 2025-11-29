package com.vvanshkkumar.tasks.Mappers;

import com.vvanshkkumar.tasks.domain.Dto.TaskDto;
import com.vvanshkkumar.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
