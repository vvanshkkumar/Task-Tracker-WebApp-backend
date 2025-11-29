package com.vvanshkkumar.tasks.Service.impl;

import com.vvanshkkumar.tasks.Repositories.TaskListRepository;
import com.vvanshkkumar.tasks.Repositories.TaskRepository;
import com.vvanshkkumar.tasks.Service.TaskService;
import com.vvanshkkumar.tasks.domain.entities.Task;
import com.vvanshkkumar.tasks.domain.entities.TaskList;
import com.vvanshkkumar.tasks.domain.entities.TaskPriority;
import com.vvanshkkumar.tasks.domain.entities.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    public TaskRepository taskRepository;
    public TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository)
    {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> getTaskList(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }


    @Override
    public Task createTask(UUID taskListId, Task task) {

        if(null != task.getId()) throw new IllegalArgumentException("Task already have an ID");
        if(null == task.getTitle() || task.getTitle().isBlank()) throw new IllegalArgumentException("Task should have a Title");

        TaskPriority taskPriority = Optional.ofNullable(task.getTaskPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        LocalDateTime now = LocalDateTime.now();

        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Invalid TaskList Provided"));

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                now,
                taskStatus,
                taskPriority,
                now,
                now,
                taskList
        );
        return taskRepository.save(taskToSave);

    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());


        TaskPriority priority = Optional.ofNullable(task.getTaskPriority())
                .orElse(TaskPriority.MEDIUM);
        existingTask.setTaskPriority(priority);


        TaskStatus status = Optional.ofNullable(task.getTaskStatus())
                .orElse(existingTask.getTaskStatus());
        existingTask.setTaskStatus(status);


        existingTask.setDueDate(task.getDueDate());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }



    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        taskRepository.delete(existingTask);
    }
}
