package br.org.acal.apicore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiCoreApplication

fun main(args: Array<String>) {
	runApplication<ApiCoreApplication>(*args)
}
