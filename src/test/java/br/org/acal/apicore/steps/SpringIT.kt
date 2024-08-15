package br.org.acal.apicore.steps

import adapter.LocalDateTimeTypeAdapter
import adapter.LocalDateTypeAdapter
import br.org.acal.apicore.ApiCoreApplication
import br.org.acal.apicore.MongoDBTestContainerConfig
import br.org.acal.apicore.resources.repository.AddressRepository
import br.org.acal.apicore.resources.repository.AreaRepository
import br.org.acal.apicore.resources.repository.CategoryRepository
import br.org.acal.apicore.resources.repository.CustomerRepository
import br.org.acal.apicore.resources.repository.InvoiceRepository
import br.org.acal.apicore.resources.repository.LinkRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.cucumber.spring.CucumberContextConfiguration
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import java.time.LocalDate
import java.time.LocalDateTime
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = [ApiCoreApplication::class])
@CucumberContextConfiguration
@ExtendWith(SpringExtension::class)
@Testcontainers
@ContextConfiguration(classes = [MongoDBTestContainerConfig::class])
class SpringIT {

    @Autowired
    lateinit var stepShared: StepShared

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var areaRepository: AreaRepository

    @Autowired
    lateinit var addressRepository: AddressRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var linkRepository: LinkRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository


    @LocalServerPort
    var serverPort = -1

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
        invoiceRepository.deleteAll()
        linkRepository.deleteAll()
        addressRepository.deleteAll()
        areaRepository.deleteAll()
        customerRepository.deleteAll()
    }

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
        .create()

}
