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
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
        request: CategoryPaginateRequest
    ): ResponseEntity<Page<CategoryPaginateResponse>> = run {
        logger.info { "Getting category /paginate by filter $request" }
        ok(paginate.execute(input = request.toEntity()).map { CategoryPaginateResponse(it) } .also {
            logger.info { "Returning category /paginate size: ${it.size}"}
        })
    }

    @GetMapping("/find")
    fun findAllByFilter(
        request: CategoryFilterRequest
    ): ResponseEntity<List<CategoryFindResponse>> = run  {
        logger.info { "Finding category by filter $request"}
        ok(findAllByFilter.execute(request.toEntity()).map { CategoryFindResponse(it) }
            .also {
                logger.info { "Returning finding category by filter, size: ${it.size}"}
        })
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<CategoryGetResponse>> = ok(
        findAll.execute(Unit).map { CategoryGetResponse(it) }
    )

    @GetMapping("/{id}")
    fun get(@Valid @PathVariable id: String): ResponseEntity<CategoryGetResponse> = run {
        logger.info { "Getting category Get/$id" }
        ok(CategoryGetResponse(get.execute(id).also {
            logger.info { "Returning category $it" }
        }))
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
    fun createLot(@Valid @RequestBody request: List<CategoryMigrateRequest>) {
        createLot.execute(request.toEntity())
    }

}
