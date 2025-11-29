package com.vvanshkkumar.tasks.Controllers;

import com.vvanshkkumar.tasks.Mappers.TaskListMapper;
import com.vvanshkkumar.tasks.Service.TaskListService;
import com.vvanshkkumar.tasks.domain.Dto.TaskListDto;
import com.vvanshkkumar.tasks.domain.entities.TaskList;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskList().stream().map(taskListMapper::toDto).toList();
    }

    @PostMapping
    public TaskListDto CreateTaskList(@RequestBody TaskListDto taskListDto){

         TaskList createdTaskList = taskListService.CreateTaskList(taskListMapper.fromDto(taskListDto));

         return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID id){
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDto taskListDto){

        TaskList taskList = taskListMapper.fromDto(taskListDto);

        TaskList updatedTaskList = taskListService.updateTaskList(id,taskList);

        return taskListMapper.toDto(updatedTaskList);

    }

    @DeleteMapping("/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID id){
        taskListService.deleteTaskList(id);
    }

}
