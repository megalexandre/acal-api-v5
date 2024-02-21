package br.org.acal.apicore.domain.datasource

interface SequenceDataSource {

    fun next(value: String): Long

}