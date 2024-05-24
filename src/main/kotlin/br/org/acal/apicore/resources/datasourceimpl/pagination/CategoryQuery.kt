package br.org.acal.apicore.resources.datasourceimpl.pagination


import br.org.acal.apicore.common.enums.CategoryValueType.PARTNER
import br.org.acal.apicore.common.enums.CategoryValueType.WATER
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

            name?.let {
                addCriteria(
                    Criteria
                        .where("name").regex("^$name", "i")
                )
            }

            water?.let {
                addCriteria(
                    Criteria.where(WATER.name.lowercase()).`is`(water)
                )
            }

            partner?.let {
                addCriteria(
                    Criteria.where(PARTNER.name.lowercase()).`is`(partner)
                )
            }
        }}
    }

}

