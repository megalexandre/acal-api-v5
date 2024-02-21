package br.org.acal.apicore.domain.usecases.sequence

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.SequenceDataSource
import org.springframework.stereotype.Service

@Service
class NextSequenceUsecase(
    private val sequence: SequenceDataSource
): Usecase<String, Long> {
    override fun execute(input: String): Long = sequence.next(input)
}

