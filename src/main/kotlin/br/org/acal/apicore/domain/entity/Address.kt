package br.org.acal.apicore.domain.entity


data class Address(

    val id: String,
    val area: Area,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
    val active: Boolean,

)
