1. ./gradlew clean build
2. run Docker, "docker compose up -d"
3. "docker ps" to check that containers are up and running
4. DB connections -> Host: localhost, port: 5432, Database: configdb, username: postgresDB, password: pass123, only one table -> configurations
5. Run project
6. in browser -> http://localhost:8080/swagger-ui/index.html#/ (swagger)
7. Open Postman (or other) to Create, Get, Update, Delete
8. POST - http://localhost:8080/api/configurations   body -> {
  "serviceName": "some service", 
  "configKey": "some key",
  "value": "some value"
}
8.GET - http://localhost:8080/api/configurations/some service
9.PUT - http://localhost:8080/api/configurations/some-service/some key body -> {
  "value": "127.0.0.120"
}
10.DELETE - http://localhost:8080/api/configurations/some service/some key
11. When doing this CRUD operations you can see in IDE terminal that Kafka is publishing ->
    - Received Kafka event: ConfigEvent[serviceName=my-service001, configKey=db.url, value=localhost, action=CREATED]
    - Received Kafka event: ConfigEvent[serviceName=my-service001, configKey=db.url, value=127.0.0.120, action=UPDATED]
    - Received Kafka event: ConfigEvent[serviceName=my-service001, configKey=db.url, value=127.0.0.120, action=DELETE]
