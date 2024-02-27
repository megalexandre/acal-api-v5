package br.org.acal.apicore

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer

abstract class MongoContainerK {

    val container = mongoContainer
    companion object {
        private const val USERNAME = "root"
        private const val PASSWORD = "example"

        val mongoContainer: GenericContainer<*> = GenericContainer<Nothing>("mongo:4.0.10").apply {
            withExposedPorts(27017)
            withReuse(true)
            withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
            withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)
        }

        @JvmStatic
        @DynamicPropertySource
        fun overrideProps(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") {
                "mongodb://${USERNAME}:${PASSWORD}@${mongoContainer.host}:${
                    mongoContainer.getMappedPort(
                        27017
                    )
                }/"
            }
        }
    }

}