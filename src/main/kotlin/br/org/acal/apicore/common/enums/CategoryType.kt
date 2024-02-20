package br.org.acal.apicore.common.enums

enum class CategoryType(val value: String) {

    FOUNDING("Sócio Fundador"),
    EFFECTIVE("Sócio Efetivo"),
    TEMPORARY("Temporário");

    companion object{
        fun get(value: String?): CategoryType? = entries.find { it.value.lowercase() == value?.lowercase() }
    }

}
