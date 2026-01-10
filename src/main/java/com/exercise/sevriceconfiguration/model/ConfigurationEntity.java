package com.exercise.sevriceconfiguration.model;

import java.time.LocalDateTime;

// import org.springframework.data.annotation.Id;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
@Table(name = "configurations")
public class ConfigurationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "config_key", nullable = false)
    private String configKey;

    @Column(name = "config_value", nullable = false)
    private String configValue;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public String getServiceName(){
        return this.serviceName;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public String getConfigKey(){
        return this.configKey;
    }

    public void setConfigKey(String configKey){
        this.configKey = configKey;
    }

    public String getConfigValue(){
        return this.configValue;
    }

    public void setConfigValue(String configValue){
        this.configValue = configValue;
    }

    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    @PrePersist
    void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
    
}
