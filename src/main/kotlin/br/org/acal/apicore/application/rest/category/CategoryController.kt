package br.org.acal.apicore.application.rest.category

import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.application.rest.category.request.CategoryCreateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryFilterRequest
import br.org.acal.apicore.application.rest.category.request.CategoryMigrateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryPaginateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryUpdateRequest
import br.org.acal.apicore.application.rest.category.response.CategoryCreateResponse
import br.org.acal.apicore.application.rest.category.response.CategoryFindResponse
import br.org.acal.apicore.application.rest.category.response.CategoryGetResponse
import br.org.acal.apicore.application.rest.category.response.CategoryPaginateResponse
import br.org.acal.apicore.application.rest.category.response.CategoryUpdateResponse
import br.org.acal.apicore.common.enums.Fixtures.Companion.FIND
import br.org.acal.apicore.common.enums.Fixtures.Companion.ID
import br.org.acal.apicore.common.enums.Fixtures.Companion.MIGRATE
import br.org.acal.apicore.common.enums.Fixtures.Companion.PAGINATE
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.usecases.category.CategoryCreateUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryDeleteUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryFindFilterUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryGetUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryPaginateByFilterUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryUpdateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
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
@RequestMapping("category", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class CategoryController(
    private val get: CategoryGetUsecase,
    private val create: CategoryCreateUsecase,
    private val delete: CategoryDeleteUsecase,
    private val update: CategoryUpdateUsecase,
    private val paginate: CategoryPaginateByFilterUsecase,
    private val findAllByFilter: CategoryFindFilterUsecase,
): Sl4jLogger() {
    @GetMapping(PAGINATE)
    @ResponseStatus(OK)
    fun paginateByFilter(
        request: CategoryPaginateRequest
    ): Page<CategoryPaginateResponse> {
        logger.info { "Getting category /paginate by filter $request" }
        return paginate.execute(request.toEntity()).map { CategoryPaginateResponse(it) } .also {
            logger.info { "Returned category /paginate size: ${it.size}"}
        }
    }

    @GetMapping(FIND)
    fun findAllByFilter(
        request: CategoryFilterRequest
    ): ResponseEntity<List<CategoryFindResponse>> = run  {
        logger.info { "Finding category by filter $request"}
        ok(findAllByFilter.execute(request.toEntity()).map { CategoryFindResponse(it) }
            .also {
                logger.info { "Returned find category by filter, size: ${it.size}"}
        })
    }

    @GetMapping(ID)
    fun get(@Valid @PathVariable @ULIDValidator id: String): ResponseEntity<CategoryGetResponse> = run {
        logger.info { "Getting category Get/$id" }
        ok(CategoryGetResponse(get.execute(id).also {
            logger.info { "Returned category $it" }
        }))
    }

    @PostMapping(MIGRATE)
    fun migrate(@Valid @RequestBody request: List<CategoryMigrateRequest>)= run{
        logger.info { "Migrating category $request" }
        created(
            request.map { create.execute( it.toEntity()) }
        ).also {
            logger.info { "Migrated category $it" }
        }
    }

    @DeleteMapping(ID)
    fun delete(@Valid @PathVariable @ULIDValidator id: String){
        logger.info { "Deleting category Get/$id" }
        delete.execute(id)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@Valid @RequestBody request: CategoryCreateRequest): CategoryCreateResponse = run {
        logger.info { "Posting category $request" }
        CategoryCreateResponse(create.execute(request.toEntity())).also {
            logger.info { "Posted category $it" }
        }
    }

    @PutMapping
    @ResponseStatus(OK)
    fun update(@Valid @RequestBody request: CategoryUpdateRequest): CategoryUpdateResponse = run {
        logger.info { "Putting category $request" }
        CategoryUpdateResponse(update.execute(request.toEntity())).also {
            logger.info { "Putted category $it" }
        }
    }

}
