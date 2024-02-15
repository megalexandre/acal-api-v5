package br.org.acal.apicore.application.rest.components.adapter

fun interface RequestAdapter<out T> {

    fun toEntity(): T

}