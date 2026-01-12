package com.exercise.sevriceconfiguration.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public record dtoConfigUpdate(
    @NotBlank String value
) implements Serializable {}