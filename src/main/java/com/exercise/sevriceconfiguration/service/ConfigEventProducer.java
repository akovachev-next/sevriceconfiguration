package com.exercise.sevriceconfiguration.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.exercise.sevriceconfiguration.dto.ConfigEvent;

@Service
public class ConfigEventProducer {
    private final KafkaTemplate<String, ConfigEvent> kafkaTemplate;
    private static final String TOPIC = "configs";

    public ConfigEventProducer(KafkaTemplate<String, ConfigEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ConfigEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
