package br.org.acal.apicore.steps

import br.org.acal.apicore.ApiCoreApplication
import br.org.acal.apicore.MongoContainer_
import br.org.acal.apicore.resources.repository.CategoryRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.cucumber.spring.CucumberContextConfiguration
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = [ApiCoreApplication::class])
@CucumberContextConfiguration
@ExtendWith(SpringExtension::class)
class SpringIT: MongoContainer_() {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @LocalServerPort
    var serverPort = -1;

    @BeforeEach
    fun setup(){
        RestAssured.port = serverPort
        resetDatabase()
    }

    private fun buildRequestSpec(): RequestSpecification {
        return RequestSpecBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
    }

    fun resetDatabase(){
        categoryRepository.deleteAll()
    }

    val gson: Gson = GsonBuilder()
        .create()

}
