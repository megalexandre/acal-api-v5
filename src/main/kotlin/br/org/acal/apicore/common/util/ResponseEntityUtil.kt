package br.org.acal.apicore.common.util


import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable

class ResponseEntityUtil {
    companion object{
        fun <T> created(@Nullable body: T): ResponseEntity<T> {
            return ResponseEntity(body, CREATED)
        }
    }
}