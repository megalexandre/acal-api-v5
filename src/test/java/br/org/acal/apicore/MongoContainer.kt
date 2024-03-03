package br.org.acal.apicore

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer

abstract class MongoContainer {
    companion object {
        var DB_CONTAINER: GenericContainer<*>? = null
        private const val USERNAME = "root"
        private const val PASSWORD = "example"

        init {
            DB_CONTAINER = GenericContainer("mongo:4.0.10")
                .withExposedPorts(27017)
                .withReuse(true)
                .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
                .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)
            DB_CONTAINER!!.start()
        }

        @DynamicPropertySource
        fun overrideProps(registry: DynamicPropertyRegistry) {
            registry.add(
                "spring.data.mongodb.uri"
            ) {
                "mongodb://" +
                        USERNAME + ":" +
                        PASSWORD + "@" +
                        DB_CONTAINER!!.host + ":" +
                        DB_CONTAINER!!.getMappedPort(27017) +
                        "/"
            }
        }
    }
}
