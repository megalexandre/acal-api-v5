package br.org.acal.apicore.application.rest.category

import br.org.acal.apicore.application.rest.category.request.CategoryCreateRequest
import br.org.acal.apicore.application.rest.category.request.toEntity
import br.org.acal.apicore.application.rest.category.response.CategoryFindAllResponse
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.usecases.category.CategoryCreateLotUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryCreateUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryFindAllUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class CategoryController(
    private val create: CategoryCreateUsecase,
    private val createLot: CategoryCreateLotUsecase,
    private val findAll: CategoryFindAllUsecase,
): Sl4jLogger() {

    @PostMapping
    fun create(@Valid @RequestBody request: CategoryCreateRequest): ResponseEntity<Category> {
        return created(
            create.execute(request.toEntity())
        )
    }

    @PostMapping("lot")
    fun createLot(@Valid @RequestBody request: List<CategoryCreateRequest>) {
        createLot.execute(request.toEntity())
    }
    @GetMapping
    fun findAll(): ResponseEntity<List<CategoryFindAllResponse>> {
        return ResponseEntity.ok(
            findAll.execute(Unit).map { CategoryFindAllResponse(it) }
        )
    }
}


