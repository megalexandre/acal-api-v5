package br.org.acal.apicore.application.rest.customer

import br.org.acal.apicore.application.rest.customer.request.CustomerCreateRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerFindByFilterRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerMigrateRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerPaginateByFilterRequest
import br.org.acal.apicore.application.rest.customer.request.CustomerUpdateRequest
import br.org.acal.apicore.application.rest.customer.request.toEntity
import br.org.acal.apicore.application.rest.customer.response.CustomerCreateResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerFindAllResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerGetResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerPaginateResponse
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import br.org.acal.apicore.domain.usecases.customer.CustomerCreateLotUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerCreateUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerFindAllByFilterUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerFindAllUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerGetUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerPaginateByFilterUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerUpdateUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerValidUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort.Direction
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
    private val paginate: CustomerPaginateByFilterUsecase,
    private val valid: CustomerValidUsecase,
): Sl4jLogger() {

    @GetMapping("paginate")
    fun paginateByFilter(
        @RequestParam(required = false) id: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) documentNumber: String?,
        @RequestParam(required = false) offset: Int?,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) field: String?,
        @RequestParam(required = false) direction: Direction?,
    ): ResponseEntity<Page<CustomerPaginateResponse>> {
        val request = CustomerPaginateByFilterRequest(
            filter = CustomerFindByFilterRequest(
                id = id,
                name = name,
                documentNumber = documentNumber,
            ),
            limitOffsetAndSort = LimitOffsetAndSort(
                offset = offset,
                size = size,
                sortField = SortField(field = field, direction = direction),
            ),

        ).also {
            logger.info {
                "Getting Paginate/ customer by: $it"
            }
        }

        return ok(
            paginate.execute(input = request.toEntity()).map { CustomerPaginateResponse(it) } .also {
                logger.info { "Returning customer /paginate $it"}
            }
        )
    }

    @GetMapping("/find")
    fun findAllByFilter(
        @RequestParam(required = false) id: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) documentNumber: String?,
    ): ResponseEntity<List<CustomerFindAllResponse>> {
        logger.info { "Querying customer Post/customer find by id $id and documentNumber $documentNumber" }

        return ok(
            findAllByFilter.execute(
                CustomerFindByFilterRequest(
                    id = id,
                    name = name,
                    documentNumber = documentNumber
                ).toEntity()).map { CustomerFindAllResponse(it) }
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
    fun create(@Valid @RequestBody request: CustomerCreateRequest): ResponseEntity<CustomerCreateResponse> {
        logger.info { "Creating Post/ customer $request" }
        return created(
            CustomerCreateResponse(create.execute(request.toEntity()).also {
                logger.info { "Created customer $it" }
            })
        )
    }

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


}