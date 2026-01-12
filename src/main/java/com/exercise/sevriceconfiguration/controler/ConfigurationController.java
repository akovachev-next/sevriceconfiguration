package com.exercise.sevriceconfiguration.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.sevriceconfiguration.dto.dtoConfigRequest;
import com.exercise.sevriceconfiguration.dto.dtoConfigResponse;
import com.exercise.sevriceconfiguration.service.ConfigurationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {
    
    private final ConfigurationService service;

    public ConfigurationController(ConfigurationService service){
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody @Valid dtoConfigRequest request){
        service.create(request);
    }

    @GetMapping("/{serviceName}")
    public List<dtoConfigResponse> get(@PathVariable("serviceName") String serviceName){
        return service.getByService(serviceName);
    }
    
    
}
