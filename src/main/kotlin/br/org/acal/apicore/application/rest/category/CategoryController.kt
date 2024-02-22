package br.org.acal.apicore.application.rest.category

import br.org.acal.apicore.application.rest.category.request.CategoryCreateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryFilterRequest
import br.org.acal.apicore.application.rest.category.request.CategoryMigrateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryPaginateRequest
import br.org.acal.apicore.application.rest.category.request.toEntity
import br.org.acal.apicore.application.rest.category.response.CategoryCreateResponse
import br.org.acal.apicore.application.rest.category.response.CategoryFindResponse
import br.org.acal.apicore.application.rest.category.response.CategoryGetResponse
import br.org.acal.apicore.application.rest.category.response.CategoryPaginateResponse
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.usecases.category.CategoryCreateLotUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryCreateUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryFindAllByFilterUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryFindAllUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryGetUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryPaginateByFilterUsecase
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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class CategoryController(
    private val get: CategoryGetUsecase,
    private val create: CategoryCreateUsecase,
    private val createLot: CategoryCreateLotUsecase,
    private val findAll: CategoryFindAllUsecase,
    private val paginate: CategoryPaginateByFilterUsecase,
    private val findAllByFilter: CategoryFindAllByFilterUsecase,
): Sl4jLogger() {

    @GetMapping("paginate")
    fun paginateByFilter(
        @RequestParam(required = false) id: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) offset: Int?,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) field: String?,
        @RequestParam(required = false) direction: Direction?,
    ): ResponseEntity<Page<CategoryPaginateResponse>> {
        val request = CategoryPaginateRequest(
            filter = CategoryFilterRequest(
                id = id,
                name = name,
                type = type,
            ),
            limitOffset = LimitOffset(offset = offset, size = size),
            sortField = SortField(field = field, direction = direction),
        ).also {
            logger.info {
                "Getting Paginate/ category by: $it"
            }
        }

        return ok(
            paginate.execute(input = request.toEntity()).map { CategoryPaginateResponse(it) } .also {
                logger.info { "Returning category /paginate $it"}
            }
        )
    }

    @GetMapping("/find")
    fun findAllByFilter(
        @RequestParam(required = false) id: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) type: String?,
    ): ResponseEntity<List<CategoryFindResponse>> {

        return ok(
            findAllByFilter.execute(
                CategoryFilterRequest(
                    id = id,
                    name = name,
                    type = type,
                ).toEntity()).map { CategoryFindResponse(it) }
                .also {
                    logger.info { "Returning category /find ${it.size}"}
                }
        )
    }

    @GetMapping("/{id}")
    fun get(@Valid @PathVariable id: String): ResponseEntity<CategoryGetResponse> {
        logger.info { "Getting category Get/$id" }
        return ok(
            CategoryGetResponse(get.execute(id).also {
                logger.info { "Returning category $it" }
            })
        )
    }

    @PostMapping
    fun create(@Valid @RequestBody request: CategoryCreateRequest): ResponseEntity<CategoryCreateResponse> = created(
        CategoryCreateResponse(create.execute(request.toEntity()))
    )

    @PostMapping("/migrate")
    fun migrate(@Valid @RequestBody request: CategoryMigrateRequest): ResponseEntity<Category> = created(
        create.execute(request.toEntity())
    )

    @PostMapping("lot")
    fun createLot(@Valid @RequestBody request: List<CategoryCreateRequest>) {
        createLot.execute(request.toEntity())
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<CategoryGetResponse>> = ok(
        findAll.execute(Unit).map { CategoryGetResponse(it) }
    )
}


