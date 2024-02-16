package br.org.acal.apicore.domain.usecases.link

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class LinkCreateUsecase(
    private val dataSource: LinkDataSource
) : Usecase<List<Link>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<Link>): Unit =
        input.forEach{ link ->
            valid(link).let {
                dataSource.save(link)
            }
        }

    private fun valid(link: Link) {

    }

}
