package br.org.acal.apicore;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@Configuration
@EnableMongoRepositories
public class MongoDBTestContainerConfig {
    @Container
    public static GenericContainer mongoDBContainer = new GenericContainer("mongo:latest")
        .withExposedPorts(27017)
        .withReuse(true)
         .withEnv("MONGO_INITDB_ROOT_USERNAME", "root")
         .withEnv("MONGO_INITDB_ROOT_PASSWORD", "example");

    static {
        mongoDBContainer.start();
        var mappedPort = mongoDBContainer.getMappedPort(27017);
        System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
    }
}