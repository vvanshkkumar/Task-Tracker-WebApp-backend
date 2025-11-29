package com.vvanshkkumar.tasks.domain.Dto;

import java.util.UUID;
import java.util.List;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
}
