package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType
import br.org.acal.apicore.common.enums.PersonType.INDIVIDUAL
import br.org.acal.apicore.common.enums.PersonType.LEGAL
import br.org.acal.apicore.common.enums.PersonType.UNKNOWN

data class DocumentNumber(
    val number: String
){
    companion object{
        private const val MAXIMUM_SIZE = 3
        private const val CPF_SIZE = 11
        private const val CNPJ_SIZE = 14

    }

    val type: PersonType = when(number.length){
        CPF_SIZE -> INDIVIDUAL
        CNPJ_SIZE -> LEGAL
        else -> UNKNOWN
    }

    val isValid: Boolean = when(type){
        INDIVIDUAL -> CPF(number).valid
        LEGAL -> CNPJ(number).valid
        UNKNOWN -> false
    }

    val isInvalid: Boolean = !isValid

    override fun toString(): String = when(number.length > MAXIMUM_SIZE){
        true -> number.substring(0, MAXIMUM_SIZE) + "*".repeat(number.length - 3)
        false -> number
    }
}
