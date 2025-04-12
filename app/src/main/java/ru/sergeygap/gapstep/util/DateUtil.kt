package ru.sergeygap.gapstep.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun Instant.convertToString(): String {
    val localDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())
    val day = localDateTime.date.dayOfMonth.toString().padStart(2, '0')
    val month = localDateTime.date.monthNumber.toString().padStart(2, '0')
    val year = localDateTime.date.year.toString()
    return "$day.$month.$year"
}

fun String.convertToInstant(): Instant {
    val parts = this.split(".")
    require(parts.size == 3) { "Неверный формат даты. Ожидается dd.MM.yyyy" }
    val day = parts[0].toIntOrNull() ?: throw IllegalArgumentException("Неверное значение дня")
    val month =
        parts[1].toIntOrNull() ?: throw IllegalArgumentException("Неверное значение месяца")
    val year =
        parts[2].toIntOrNull() ?: throw IllegalArgumentException("Неверное значение года")
    val localDateTime = LocalDateTime(year, month, day, 0, 0, 0)
    return localDateTime.toInstant(TimeZone.currentSystemDefault())
}