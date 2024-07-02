package br.org.acal.apicore.application.rest.link.request

import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import org.springframework.data.domain.Sort
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
class LinkPaginateRequestFilter(
    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val active: Boolean?,
    @RequestParam(required = false) val categoryId: String?,

    @RequestParam(required = false) val offset: Int?,
    @RequestParam(required = false) val size: Int?,
    @RequestParam(required = false) val field: String?,
    @RequestParam(required = false) val direction: Sort.Direction?,
){

    fun toLinkPaginateRequest(): LinkPageFilter =
        LinkPageFilter(
            filter = LinkFilter(
                id = id,
                categoryId = categoryId,
                active = active
            ),

            limitOffsetAndSort = LimitOffsetAndSort(
                offset = offset,
                size = size,
                sortField = SortField(field = field, direction = direction),
            )
        )

}