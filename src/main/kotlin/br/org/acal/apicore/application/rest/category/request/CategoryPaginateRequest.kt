package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.common.util.DirectionUtil
import br.org.acal.apicore.domain.dto.pagination.category.CategoryPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
data class CategoryPaginateRequest (

    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val name: String?,
    @RequestParam(required = false) val type: String?,
    @RequestParam(required = false) val water: String?,
    @RequestParam(required = false) val partner: String?,

    @RequestParam(required = false) val offset: Int?,
    @RequestParam(required = false) val size: Int?,

    @RequestParam(required = false) val field: String?,
    @RequestParam(required = false) val direction: String?,

    private val filter: CategoryFilterRequest? = null,
    private val limitOffsetAndSort: LimitOffsetAndSort? = null,
    private val sortField: SortField? = null,

    ){

    fun toEntity(): CategoryPageFilter = CategoryPageFilter(

        filter = CategoryFilterRequest(
            id = id,
            name = name,
            type = type,
            water = water,
            partner = partner,
        ).toEntity(),

        limitOffsetAndSort = LimitOffsetAndSort(
            offset = offset,
            size = size,
            sortField = SortField(field = field ?: "id", direction = DirectionUtil.of(direction))
        )
    )

}