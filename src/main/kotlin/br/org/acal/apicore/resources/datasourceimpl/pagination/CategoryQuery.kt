package br.org.acal.apicore.resources.datasourceimpl.pagination


import br.org.acal.apicore.domain.dto.pagination.link.CategoryFilter
import br.org.acal.apicore.domain.dto.pagination.link.InvoiceFilter
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class CategoryQuery: PaginateAndSortQuery<CategoryFilter>() {

    override fun query(filter: CategoryFilter?): Query = Query().apply {
        filter?.let {with(filter){

            if (!id.isNullOrEmpty()) {
                addCriteria(
                    Criteria
                        .where("id").`is`(id)
                )
            }

        }}
    }

}

