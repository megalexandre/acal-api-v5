package br.org.acal.apicore.common.enums

enum class Reason {

    INVOICE,
    CATEGORY,
    WATER,
    HYDROMETER,
    PARTNERSHIP;
    companion object {
        fun get(value: String?): Reason? = Reason.entries.find { it.name.lowercase() == value?.lowercase() }
    }
}