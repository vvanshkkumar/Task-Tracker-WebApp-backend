package com.vvanshkkumar.tasks.Service;

import com.vvanshkkumar.tasks.domain.entities.Task;
import com.vvanshkkumar.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {

    List<TaskList>  listTaskList();
    TaskList CreateTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID id, TaskList taskList);
    void deleteTaskList(UUID id);
}
