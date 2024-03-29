package br.org.acal.apicore.infrastructure

import br.org.acal.apicore.common.util.ResponseEntityUtil
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import java.time.LocalDateTime
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AppAdvice: Sl4jLogger(){

	@ExceptionHandler(value = [InvalidUsecaseException::class])
	fun e1 (ex: RuntimeException) = ResponseEntity.badRequest().body(
		mapOf(
			"time" to LocalDateTime.now().toString(),
			"message" to ex.message
		)
	)

	@ExceptionHandler(value = [DataNotFoundException::class])
	fun e2 (ex: RuntimeException) = ResponseEntityUtil.noContent(
		mapOf(
			"time" to LocalDateTime.now().toString(),
			"message" to ex.message
		).toString()
	)

}