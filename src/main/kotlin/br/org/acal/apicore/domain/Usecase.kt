package br.org.acal.apicore.domain

fun interface Usecase<in Input, out Output> {
    fun execute(input: Input): Output
}