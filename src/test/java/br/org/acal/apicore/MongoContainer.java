package br.org.acal.apicore;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;

public abstract class MongoContainer {

    public static final GenericContainer DB_CONTAINER;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "example";

    static  {
        DB_CONTAINER = new GenericContainer("mongo:4.0.10")
                .withExposedPorts(27017)
                .withReuse(true)
                .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
                .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD);


        DB_CONTAINER.start();

    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () ->
            "mongodb://" +
                    USERNAME + ":" +
                    PASSWORD + "@" +
                    DB_CONTAINER.getHost() + ":" +
                    DB_CONTAINER.getMappedPort(27017) +
                "/"
        );
    }
}
