package br.org.acal.apicore.resources.datasourceimpl

import br.org.acal.apicore.domain.datasource.SequenceDataSource
import br.org.acal.apicore.resources.document.SequenceDocument
import br.org.acal.apicore.resources.repository.SequenceRepository
import org.springframework.stereotype.Service

@Service
class SequenceDataSourceImpl(
    private val repository: SequenceRepository,
): SequenceDataSource {

    companion object{
        private const val INITIAL_VALUE = 1L
    }
    override fun next(value: String): Long {
        val sequence: SequenceDocument? = repository.findById(value).orElse(null)

        return if (sequence != null) {
            repository.save(sequence.copy(value = sequence.value +1))
            sequence.value
        } else {
            repository.save(SequenceDocument(id = value, value = INITIAL_VALUE))
            INITIAL_VALUE
        }
    }

}