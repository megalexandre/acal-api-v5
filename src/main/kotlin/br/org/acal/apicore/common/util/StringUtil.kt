package br.org.acal.apicore.common.util

import br.org.acal.apicore.common.enums.Fixtures.Companion.REFERENCE_REGEX
import java.text.Normalizer.Form.NFD
import java.text.Normalizer.normalize

fun String?.isNotNullOrEmpty(): Boolean = !this.isNullOrEmpty()

fun String.normalize(): String =
    normalize(this, NFD)
        .replace("[^\\p{ASCII}]".toRegex(), "")
        .trim()
        .lowercase()

fun String.isValidReference(): Boolean  = REFERENCE_REGEX.toRegex().matches(this)