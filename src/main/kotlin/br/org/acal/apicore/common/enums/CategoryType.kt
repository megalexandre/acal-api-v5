package br.org.acal.apicore.common.enums

enum class CategoryType(val value: String) {

    FOUNDING("Sócio Fundador"),
    EFFECTIVE("Sócio Efetivo"),
    TEMPORARY("Temporario");

    companion object{
        fun get(value: String?): CategoryType? {
            return entries.find { it.value == value }
        }
    }

}
