package com.exercise.sevriceconfiguration.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.exercise.sevriceconfiguration.dto.ConfigEvent;

@Component
public class KafkaTestListener {

    @KafkaListener(topics = "configs", groupId = "test-group-new1")
    public void listen(ConfigEvent event) {
        System.out.println("Received Kafka event: " + event);
    }
}
