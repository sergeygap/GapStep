package ru.sergeygap.gapstep.data.dto

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import ru.sergeygap.gapstep.domain.entity.HabitType

data class HabitDto(
    val id: Int = 0,
    val name: String = "Чтение",
    val createdDate: Instant = Clock.System.now(),
    val description: String = "Чтение книг по вечерам",
    val priority: String = "Важная",
    val type: HabitType = HabitType.Useful,
    val count: Int = 0,
    val period: Int = 0,
    val color: Int = 0xFFFFFFAA.toInt()
)
