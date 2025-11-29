package com.vvanshkkumar.tasks.Controllers;

import com.vvanshkkumar.tasks.Mappers.TaskMapper;
import com.vvanshkkumar.tasks.Repositories.TaskRepository;
import com.vvanshkkumar.tasks.Service.TaskService;
import com.vvanshkkumar.tasks.domain.Dto.TaskDto;
import com.vvanshkkumar.tasks.domain.entities.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {

     private TaskService taskService;
     private TaskMapper taskMapper;

     public TaskController(TaskService taskService, TaskMapper taskMapper){
         this.taskService = taskService;
         this.taskMapper = taskMapper;
     }

     @GetMapping
    public List<TaskDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){
         return taskService.getTaskList(taskListId).stream().map(taskMapper::toDto).toList();
     }

     @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId,
                      @RequestBody TaskDto taskDto){
         Task taskCreated =  taskService.createTask(taskListId, taskMapper.fromDto(taskDto));

         return taskMapper.toDto(taskCreated);
     }

     @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId,
                                     @PathVariable("task_id") UUID taskId){
         return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto) {

        Task updated = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto)
        );

        return taskMapper.toDto(updated);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {

        taskService.deleteTask(taskListId, taskId);
    }

}
