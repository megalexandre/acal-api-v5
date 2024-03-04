package br.org.acal.apicore.resources.datasourceimpl.pagination


import br.org.acal.apicore.domain.dto.pagination.category.CategoryFilter
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

            if (type != null) {
                addCriteria(
                    Criteria
                        .where("type").`is`(type)
                )
            }

            if (name != null) {
                addCriteria(
                    Criteria
                        .where("name").`is`(name)
                )
            }

        }}
    }

}

