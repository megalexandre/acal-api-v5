package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType
import br.org.acal.apicore.common.enums.PersonType.INDIVIDUAL
import br.org.acal.apicore.common.enums.PersonType.LEGAL
import br.org.acal.apicore.common.enums.PersonType.UNKNOWN

data class DocumentNumber(
    private var value: String
){
    companion object{
        private const val MAXIMUM_LOG_SIZE = 3
        private const val CPF_SIZE = 11
        private const val CNPJ_SIZE = 14
    }
    init {
        if (value.length > CNPJ_SIZE && value.startsWith('0')) {
            value = value.padStart(14, '0').substring(value.length - 14)
        }
    }

    val number: String
        get() = value

    val type: PersonType = when(value.length){
        CPF_SIZE -> INDIVIDUAL
        CNPJ_SIZE -> LEGAL
        else -> UNKNOWN
    }

    val isValid: Boolean = when(type){
        INDIVIDUAL -> CPF(value).valid
        LEGAL -> CNPJ(value).valid
        UNKNOWN -> false
    }

    val isInvalid: Boolean = !isValid

    override fun toString(): String = when(value.length >= MAXIMUM_LOG_SIZE){
        true -> value.substring(0, MAXIMUM_LOG_SIZE) + "*".repeat(value.length - 3)
        false -> value
    }
}
