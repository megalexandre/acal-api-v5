package br.org.acal.apicore.domain.entity

data class CPF(
    val value: String
){
    companion object{
        private const val MAXIMUM_SIZE = 3
        private const val CPF_SIZE = 11
        private const val CPF_REGEX = "[^0-9]"
        private const val EMPTY = ""
        private const val CHARACTER_COMPLETE = '0'
    }

    val number: String
        get() = when(value.length){
            CPF_SIZE -> value
            else -> value.padStart(CPF_SIZE, CHARACTER_COMPLETE)
        }

    val valid: Boolean
        get() = this.isValidCPF()

    val notValid: Boolean
        get() = !this.isValidCPF()


    private fun isValidCPF(): Boolean {

        val clean: String = value.replace(CPF_REGEX.toRegex(), EMPTY).also {

            if (it.length != CPF_SIZE) {
                return false
            }

            if (it.all { i ->  i == it[0] }) {
                return false
            }
        }

        val digit1 = calculateDigit(clean.substring(0, 9))
        val digit2 = calculateDigit(clean.substring(0, 9) + digit1)

        return clean.endsWith("$digit1$digit2")
    }

    private fun calculateDigit(partialCpf: String): Int {
        val digit = partialCpf.indices.sumOf { partialCpf[it].toString().toInt() * (partialCpf.length + 1 - it) } % 11
        return if (digit < 2) 0 else 11 - digit
    }

    override fun toString(): String = when(value.length > MAXIMUM_SIZE){
        true ->  value.substring(0, MAXIMUM_SIZE) + "*".repeat(value.length - MAXIMUM_SIZE)
        false -> value
    }
}
