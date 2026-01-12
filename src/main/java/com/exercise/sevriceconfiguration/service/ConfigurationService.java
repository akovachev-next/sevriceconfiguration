package com.exercise.sevriceconfiguration.service;

import java.util.List;
import java.util.Optional;

import com.exercise.sevriceconfiguration.dto.ConfigEvent;
import com.exercise.sevriceconfiguration.dto.dtoConfigRequest;
import com.exercise.sevriceconfiguration.dto.dtoConfigResponse;
import com.exercise.sevriceconfiguration.dto.dtoConfigUpdate;
import com.exercise.sevriceconfiguration.model.ConfigurationEntity;
import com.exercise.sevriceconfiguration.repo.ConfigurationRepository;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

@Service
public class ConfigurationService {

    private final ConfigurationRepository repository;
    private final ConfigEventProducer producer;

    public ConfigurationService(ConfigurationRepository repository, 
            ConfigEventProducer producer
    ){
        this.repository = repository;
        this.producer = producer;
    }

    @CacheEvict(value = "configs", allEntries = true)
    public void create(dtoConfigRequest request){
        ConfigurationEntity entity = new ConfigurationEntity();
        entity.setServiceName(request.serviceName());
        entity.setConfigKey(request.configKey());
        entity.setConfigValue(request.value());
        repository.save(entity);
        String action = "CREATED";
        producer.publish(new ConfigEvent(request.serviceName(),
                                         request.configKey(), 
                                         request.value(),
                                         action));
    }

    @Cacheable("configs")
    public List<dtoConfigResponse> getByService(String serviceName){
        System.out.println(">>> FETCHING FROM DATABASE");
        return repository.findByServiceName(serviceName)
        .stream().map(r -> new dtoConfigResponse(r.getConfigKey(), r.getConfigValue()))
        .toList();
    }

    @CachePut(value = "configs", key = "#p0")
    public List<dtoConfigResponse> update(
        String serviceName,
        String configKey,
        dtoConfigUpdate update) {
            String action = "";
        Optional<ConfigurationEntity> existing =
            repository.findByServiceName(serviceName)
                      .stream()
                      .filter(c -> c.getConfigKey().equals(configKey))
                      .findFirst();

        if (existing.isPresent()) {
            ConfigurationEntity entity = existing.get();
            entity.setConfigValue(update.value());
            repository.save(entity);
            action = "UPDATED";
        } else {
            ConfigurationEntity entity = new ConfigurationEntity();
            entity.setServiceName(serviceName);
            entity.setConfigKey(configKey);
            entity.setConfigValue(update.value());
            repository.save(entity);
            action = "CREATED";
        }
        producer.publish(new ConfigEvent(serviceName, configKey, update.value(), action));
        return getByService(serviceName);
    }
    
    @CacheEvict(value = "configs", key = "#p0")
    public void delete(String serviceName, String configKey) {
        repository.findByServiceName(serviceName)
                .stream()
                .filter(c -> c.getConfigKey().equals(configKey))
                .findFirst()
                .ifPresent(entity -> {
                String oldValue = entity.getConfigValue(); 
                repository.delete(entity);

                String action = "DELETE";
                // ConfigEvent event = new ConfigEvent(serviceName, configKey, oldValue, action);
                producer.publish(new ConfigEvent(serviceName, configKey, oldValue, action));
            });      
    }

}
