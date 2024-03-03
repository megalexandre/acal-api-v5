package br.org.acal.apicore

class MongoContainer {
    /*
     @get:Rule
     val mongoDbContainer: GenericContainer<*> = GenericContainer("mongo:4.0.10")
         .withExposedPorts(27017)
         .withReuse(true)
         .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
         .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)

     @BeforeAll
     fun setup() {
         mongoDbContainer.start()

         val mongoDbUrl = mongoDbContainer.getReplicaSetUrl()
         val mongoDbUsername = mongoDbContainer.username
         val mongoDbPassword = mongoDbContainer.password

         System.setProperty("spring.data.mongodb.uri", mongoDbUrl)
         System.setProperty("spring.data.mongodb.username", mongoDbUsername)
         System.setProperty("spring.data.mongodb.password", mongoDbPassword)
     }


    companion object {
        var DB_CONTAINER: GenericContainer<*>? = null
        private const val USERNAME = "root"
        private const val PASSWORD = "example"

        init {
            DB_CONTAINER =

            DB_CONTAINER!!.start()
        }

        @PostConstruct
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
     */
}
