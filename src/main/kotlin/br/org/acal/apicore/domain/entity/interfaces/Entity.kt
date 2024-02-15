package br.org.acal.apicore.domain.entity.interfaces

import java.time.LocalDateTime

interface Entity {
    val createdAt: LocalDateTime
    val createdBy: String?
}