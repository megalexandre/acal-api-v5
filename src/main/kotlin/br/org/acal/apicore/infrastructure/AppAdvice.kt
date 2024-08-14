package br.org.acal.apicore.infrastructure

import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.noContent
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.exception.InvalidRequestException
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import java.time.LocalDateTime
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AppAdvice: Sl4jLogger(){

	@ExceptionHandler(value = [InvalidUsecaseException::class, InvalidRequestException::class])
	fun e1 (ex: RuntimeException) = badRequest().body(writeError(ex))

	@ExceptionHandler(value = [DataNotFoundException::class])
	fun e2 (ex: RuntimeException) = noContent(writeError(ex))

	private fun writeError(ex: RuntimeException) =
		mapOf(
			"time" to LocalDateTime.now().toString(),
			"message" to ex.message
		).toString()

}