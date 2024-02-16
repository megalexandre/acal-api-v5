package br.org.acal.apicore.resources.datasourceimpl.pagination


import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class LinkQuery: PaginateAndSortQuery<LinkFilter>() {

    override fun query(filter: LinkFilter?): Query = Query().apply {
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

