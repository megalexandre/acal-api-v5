package br.org.acal.apicore.resources.datasourceimpl.pagination


import br.org.acal.apicore.domain.dto.pagination.customer.CustomerFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class CustomerQuery: PaginateAndSortQuery<CustomerFilter>() {

    override fun query(filter: CustomerFilter?): Query = Query().apply {
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

