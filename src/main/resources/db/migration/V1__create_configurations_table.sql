CREATE TABLE configurations (
    id BIGSERIAL PRIMARY KEY,
    service_name VARCHAR(100) NOT NULL,
    config_key VARCHAR(200) NOT NULL,
    config_value TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_config_service
    ON configurations(service_name);

CREATE UNIQUE INDEX uk_service_key
    ON configurations(service_name, config_key);
