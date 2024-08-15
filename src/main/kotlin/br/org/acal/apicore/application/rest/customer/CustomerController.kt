package br.org.acal.apicore.application.rest.customer

import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.application.rest.customer.request.CustomerCreateRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerFilterRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerMigrateRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerPaginateRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerUpdateRequest
import br.org.acal.apicore.application.rest.customer.request.toEntity
import br.org.acal.apicore.application.rest.customer.response.CustomerCreateResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerFindAllResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerGetResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerPaginateResponse
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.usecases.customer.CustomerCreateLotUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerCreateUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerDeleteUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerFindAllByFilterUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerFindAllUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerGetUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerPaginateUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerUpdateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customer", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class CustomerController(
    private val create: CustomerCreateUsecase,
    private val update: CustomerUpdateUsecase,
    private val createLot: CustomerCreateLotUsecase,
    private val get: CustomerGetUsecase,
    private val findAll: CustomerFindAllUsecase,
    private val findAllByFilter: CustomerFindAllByFilterUsecase,
    private val paginate: CustomerPaginateUsecase,
    private val delete: CustomerDeleteUsecase,
): Sl4jLogger() {

    @GetMapping("paginate")
    fun paginateByFilter(
        customerPaginateRequest: CustomerPaginateRequest,
    ): ResponseEntity<Page<CustomerPaginateResponse>> = ok(
        paginate.execute(input = customerPaginateRequest.toCustomerPageFilter())
            .map { CustomerPaginateResponse(it) }
            .also {
                logger.info { "Returning customer /paginate $it"}
            }
    )

    @GetMapping("/find")
    fun findAllByFilter(
        customerFilterRequest: CustomerFilterRequest
    ): ResponseEntity<List<CustomerFindAllResponse>> {
        logger.info {
            "Querying customer Post/customer find by $customerFilterRequest"
        }

        return ok(
            findAllByFilter.execute(customerFilterRequest.toCustomerFilter()).map { CustomerFindAllResponse(it) }
            .also {
                logger.info { "Returning customer /find ${it.size}"}
            }
        )
    }

    @GetMapping("/{id}")
    fun get(@Valid @PathVariable id: String): ResponseEntity<CustomerGetResponse> {
        logger.info { "Getting customer Get/$id" }
        return ok(
            CustomerGetResponse(get.execute(id).also {
                logger.info { "Returning customer $it" }
            })
        )
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<CustomerFindAllResponse>> {
        logger.info { "Querying customer Get/" }
        return ok(
            findAll.execute(Unit).map { CustomerFindAllResponse(it) }.also {
                logger.info { "Find all customer: size ${it.size}" }
            }
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CustomerCreateRequest): CustomerCreateResponse =
        CustomerCreateResponse(create.execute(request.toEntity()).also {
            logger.info { "Created customer $it" }
        })

    @PutMapping
    fun update(@Valid @RequestBody request: CustomerUpdateRequest): ResponseEntity<CustomerCreateResponse> {
        logger.info { "Updating Put/ customer $request" }
        return created(
            CustomerCreateResponse(update.execute(request.toEntity()).also {
                logger.info { "Updated customer $it" }
            })
        )
    }

    @PostMapping("lot")
    fun createLot(@Valid @RequestBody request: List<CustomerCreateRequest>) {
        logger.info { "Creating Post/ customer $request" }
        createLot.execute(request.toEntity())
    }

    @PostMapping("migrate")
    fun migrate(@Valid @RequestBody request: List<CustomerMigrateRequest>) {
        logger.info { "Creating Post/ customer $request" }
        createLot.execute(request.toEntity())
    }
    @DeleteMapping("id")
    fun delete(@Valid @PathVariable @ULIDValidator id: String){
        logger.info { "Deleting customer Get/$id" }
        delete.execute(id)
    }

}