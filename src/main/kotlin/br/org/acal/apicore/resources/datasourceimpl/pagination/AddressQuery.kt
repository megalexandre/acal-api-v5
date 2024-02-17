package br.org.acal.apicore.resources.datasourceimpl.pagination


import br.org.acal.apicore.domain.dto.pagination.link.AddressFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class AddressQuery: PaginateAndSortQuery<AddressFilter>() {

    override fun query(filter: AddressFilter?): Query = Query().apply {
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

