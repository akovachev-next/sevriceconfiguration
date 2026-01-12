package com.exercise.sevriceconfiguration.dto;

public record ConfigEvent(
    String serviceName,
    String configKey,
    String value,
    String action
) {}
