package br.org.acal.apicore.domain.usecases.link

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.dto.pagination.link.LinkPageFilter
import br.org.acal.apicore.domain.entity.Link
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class LinkPaginateUsecase(
    private val dataSource: LinkDataSource
) : Usecase<LinkPageFilter, Page<Link>> {

    override fun execute(input: LinkPageFilter): Page<Link> = dataSource.paginateByFilter(input)

}
