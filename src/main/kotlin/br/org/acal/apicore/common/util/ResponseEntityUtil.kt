package br.org.acal.apicore.common.util


import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.lang.Nullable

class ResponseEntityUtil {
    companion object{
        fun <T> created(@Nullable body: T): ResponseEntity<T> = ResponseEntity(body, CREATED)
        fun <T> noContent(@Nullable body: T): ResponseEntity<T> = status(NO_CONTENT).body(body)

    }




}