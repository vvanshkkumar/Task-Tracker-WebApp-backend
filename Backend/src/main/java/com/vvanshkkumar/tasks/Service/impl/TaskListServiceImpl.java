package com.vvanshkkumar.tasks.Service.impl;

import com.vvanshkkumar.tasks.Repositories.TaskListRepository;
import com.vvanshkkumar.tasks.Service.TaskListService;
import com.vvanshkkumar.tasks.domain.entities.TaskList;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList CreateTaskList(TaskList taskList){

        if(taskList.getId() != null) {
            throw new IllegalArgumentException("Task List already has an ID");
        }
        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("The Task List must have an ID");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {

        if(null == taskList.getId()) throw new IllegalArgumentException("ID cannot be found...TRY AGAIN!");

        if(!Objects.equals(taskList.getId(),id)) throw new IllegalArgumentException("User trying to modify the TaskList ID...NOT ALLOWED!");

        TaskList existingTaskList = taskListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TaskList with this ID does not exist"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());

        return taskListRepository.save(existingTaskList);

    }

    @Override
    public void deleteTaskList(UUID id) {
        taskListRepository.deleteById(id);
    }


}
