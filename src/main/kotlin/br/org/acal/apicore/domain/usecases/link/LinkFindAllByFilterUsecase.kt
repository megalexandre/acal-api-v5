package br.org.acal.apicore.domain.usecases.link

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service


@Service
class LinkFindAllByFilterUsecase(
    private val dataSource: LinkDataSource
) : Usecase<LinkFilter, List<Link>>, Sl4jLogger() {

    override fun execute(input: LinkFilter): List<Link> = dataSource.findByFilter(filter = input)

}

