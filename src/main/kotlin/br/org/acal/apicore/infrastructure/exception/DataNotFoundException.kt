package br.org.acal.apicore.infrastructure.exception

class DataNotFoundException(override val message: String): RuntimeException(message)