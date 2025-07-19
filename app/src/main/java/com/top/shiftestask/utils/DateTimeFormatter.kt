package com.top.shiftestask.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun isoToDmy(iso: String): String {
    return try {
        val dateTime = OffsetDateTime.parse(iso)
        val localDate = dateTime.toLocalDate()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        localDate.format(formatter)
    } catch (e: Exception) {
        iso
    }
}