package com.exercise.sevriceconfiguration.dto;

import jakarta.validation.constraints.NotBlank;

public record dtoConfigRequest(
    @NotBlank String serviceName,
    @NotBlank String key,
    @NotBlank String value
) {}

