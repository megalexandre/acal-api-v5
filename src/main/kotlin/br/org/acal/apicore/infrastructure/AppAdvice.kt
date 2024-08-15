package br.org.acal.apicore.infrastructure

import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.noContent
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.exception.InvalidRequestException
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import java.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

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

	@ExceptionHandler(MethodArgumentNotValidException::class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
		val errors = mutableMapOf<String, String>()

		for (error in ex.bindingResult.allErrors) {
			val fieldName = if (error is FieldError) error.field else "error"
			val errorMessage = error.defaultMessage ?: "Invalid value"
			errors[fieldName] = errorMessage
		}

		return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
	}

	@ExceptionHandler(HttpMessageNotReadableException::class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): Map<String, String> {
		return mapOf("error" to "Invalid request format")
	}

}