package com.exercise.sevriceconfiguration.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.sevriceconfiguration.model.ConfigurationEntity;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
    List<ConfigurationEntity> findByServiceName(String serviceName);
}
