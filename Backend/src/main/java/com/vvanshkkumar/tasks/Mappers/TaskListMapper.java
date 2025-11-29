package com.vvanshkkumar.tasks.Mappers;

import com.vvanshkkumar.tasks.domain.Dto.TaskListDto;
import com.vvanshkkumar.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskListDto toDto(TaskList taskList);
    TaskList fromDto(TaskListDto taskListDto);
}
