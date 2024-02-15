package br.org.acal.apicore.domain.entity

data class PhoneNumber(
    val preferential: Boolean,
    val number: String,
    val isWhatApp: Boolean
)