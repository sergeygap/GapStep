package ru.sergeygap.gapstep.data.mapper

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import ru.sergeygap.gapstep.data.dto.HabitDto
import ru.sergeygap.gapstep.domain.entity.Habit

object Mapper {

    fun mapDtoToEntity(dto: HabitDto) = Habit(
        id = dto.id,
        name = dto.name,
        createdDate = dto.createdDate.convertToString(),
        description = dto.description,
        priority = dto.priority,
        type = dto.type,
        count = dto.count,
        period = dto.period,
        color = dto.color,
    )

    fun mapEntityToDto(entity: Habit) = HabitDto(
        id = entity.id,
        name = entity.name,
        createdDate = entity.createdDate.convertToInstant(),
        description = entity.description,
        priority = entity.priority,
        type = entity.type,
        count = entity.count,
        period = entity.period,
        color = entity.color,
    )

    private fun Instant.convertToString(): String {
        val localDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())
        val day = localDateTime.date.dayOfMonth.toString().padStart(2, '0')
        val month = localDateTime.date.monthNumber.toString().padStart(2, '0')
        val year = localDateTime.date.year.toString()
        return "$day.$month.$year"
    }

    private fun String.convertToInstant(): Instant {
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

}