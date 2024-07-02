package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType
import br.org.acal.apicore.common.enums.PersonType.INDIVIDUAL
import br.org.acal.apicore.common.enums.PersonType.LEGAL

data class DocumentNumber(
    private val value: String
){
    companion object{
        private const val MAXIMUM_LOG_SIZE = 3
        private const val CPF_SIZE = 11
    }

    private val cpf = CPF(value)
    private val cnpj = CNPJ(value)

    val number: String
        get() = when(type){
            INDIVIDUAL -> cpf.number
            LEGAL -> cnpj.number
            else -> value
        }

    val type: PersonType = when{
        cpf.valid -> INDIVIDUAL
        cnpj.valid -> LEGAL
        else -> PersonType.UNKNOWN
    }

    val isValid: Boolean = cpf.valid || cnpj.valid

    val isInvalid: Boolean = !isValid

    override fun toString(): String = when(value.length >= MAXIMUM_LOG_SIZE){
        true -> value.substring(0, MAXIMUM_LOG_SIZE) + "*".repeat(value.length - 3)
        false -> value
    }
}
