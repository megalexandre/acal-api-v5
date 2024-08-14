package br.org.acal.apicore.infrastructure.exception

class InvalidRequestException(override val message: String): RuntimeException(message)