package br.org.acal.apicore.domain.usecases.link

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.info
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class  LinkGetUsecase(
    private val dataSource: LinkDataSource
) : Usecase<String, Link>, Sl4jLogger() {

    @Transactional
    override fun execute(input: String): Link =
        dataSource.findById(input) ?: throw DataNotFoundException("does not exists link with id: $input")
        .also {
            logger.info { "getting link by id $input was interrupted because does not exists invoice with id" }
        }

}
