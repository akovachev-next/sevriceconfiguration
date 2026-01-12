package com.exercise.sevriceconfiguration.service;

import java.util.List;

import com.exercise.sevriceconfiguration.dto.dtoConfigRequest;
import com.exercise.sevriceconfiguration.dto.dtoConfigResponse;
import com.exercise.sevriceconfiguration.model.ConfigurationEntity;
import com.exercise.sevriceconfiguration.repo.ConfigurationRepository;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
public class ConfigurationService {

    private final ConfigurationRepository repository;

    public ConfigurationService(ConfigurationRepository repository){
        this.repository = repository;
    }

    @CacheEvict(value = "configs", key = "#request.serviceName()")
    public void create(dtoConfigRequest request){
        ConfigurationEntity entity = new ConfigurationEntity();
        entity.setServiceName(request.serviceName());
        entity.setConfigKey(request.key());
        entity.setConfigValue(request.value());
        repository.save(entity);
    }

    @Cacheable("configs")
    public List<dtoConfigResponse> getByService(String serviceName){
        System.out.println(">>> FETCHING FROM DATABASE");
        return repository.findByServiceName(serviceName)
        .stream().map(r -> new dtoConfigResponse(r.getConfigKey(), r.getConfigValue()))
        .toList();
    }
}
