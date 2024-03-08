package br.org.acal.apicore.domain.entity

data class Link (

    val id: String,
    val customer: Customer,
    val address: Address,
    val category: Category,

    val suspended: Boolean,
    val active: Boolean,
)