package br.org.acal.apicore.common.util

import java.text.Normalizer.Form.NFD
import java.text.Normalizer.normalize

fun String?.isNotNullOrEmpty(): Boolean = !this.isNullOrEmpty()

fun String.normalize(): String =
    normalize(this, NFD)
        .replace("[^\\p{ASCII}]".toRegex(), "")
        .trim()
        .lowercase()
