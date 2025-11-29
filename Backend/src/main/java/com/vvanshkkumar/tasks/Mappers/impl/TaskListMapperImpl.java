package com.vvanshkkumar.tasks.Mappers.impl;

import com.vvanshkkumar.tasks.domain.Dto.TaskDto;
import com.vvanshkkumar.tasks.domain.Dto.TaskListDto;
import com.vvanshkkumar.tasks.Mappers.TaskListMapper;
import com.vvanshkkumar.tasks.Mappers.TaskMapper;
import com.vvanshkkumar.tasks.domain.entities.Task;
import com.vvanshkkumar.tasks.domain.entities.TaskList;
import com.vvanshkkumar.tasks.domain.entities.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {


    TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {

        List<Task> tasks = null;

        if (taskListDto.tasks() != null) {
            tasks = new ArrayList<>();

            for (TaskDto dto : taskListDto.tasks()) {
                tasks.add(taskMapper.fromDto(dto));
            }
        }
        return new TaskList(taskListDto.id(),
                           taskListDto.title(),
                           taskListDto.description(),
                           tasks,
                            null,
                            null);
    }


    @Override
    public TaskListDto toDto(TaskList taskList) {

        int taskCount = 0;

        if(taskList.getTasks()!=null){
            taskCount = taskList.getTasks().size();
        }

        return new TaskListDto(taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                taskCount,
                Calculateprogress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(tasks->tasks.stream().map(taskMapper::toDto).toList()).orElse(null));
    }

    private Double Calculateprogress(List<Task> tasks){

        if(tasks==null) return null;

        long closedCount = tasks.stream().filter(task -> TaskStatus.CLOSED==task.getTaskStatus()).count();

        return (double) closedCount/tasks.size();

    }

}
