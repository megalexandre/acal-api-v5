package br.org.acal.apicore.common.enums

enum class CategoryValueType(val value: String) {

    WATER("Água"),
    PARTNER("Sócio");
    companion object {
        fun get(value: String?): CategoryValueType? = entries.find { it.value.lowercase() == value?.lowercase() }
    }

}
