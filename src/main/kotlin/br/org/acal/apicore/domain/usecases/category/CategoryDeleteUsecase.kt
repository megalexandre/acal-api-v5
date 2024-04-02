package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional


@Service
class  CategoryDeleteUsecase(
    private val dataSource: CategoryDataSource,
    private val linkDataSource: LinkDataSource,
) : Usecase<Category, Unit>, Sl4jLogger() {

    @Transactional(propagation = REQUIRES_NEW)
    override fun execute(input: Category) {
        valid(input).let {
            dataSource.delete(input.id)
        }
    }

    private fun valid(category: Category){
        if(linkDataSource.findByFilter(LinkFilter(categoryId = category.id)).isNotEmpty()) {
            throw InvalidUsecaseException("Can't delete because have link associated")
        }
    }

}
