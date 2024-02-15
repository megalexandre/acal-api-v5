package br.org.acal.apicore.resources.document.interfaces

import java.time.LocalDateTime

interface DocumentItem {
    val createdAt: LocalDateTime
    val createdBy: String?
}