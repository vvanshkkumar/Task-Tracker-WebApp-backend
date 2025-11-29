package com.vvanshkkumar.tasks.domain.Dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
