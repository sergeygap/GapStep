package ru.sergeygap.gapstep.domain.entity

data class Habit(
    val id: Int = 0,
    val name: String = "Чтение",
    val description: String = "Чтение книг по вечерам",
    val priority: String = "Важная",
    val type: HabitType = HabitType.Useful,
    val count: Int = 0,
    val period: Int = 0,
    val color: Int = 0xFFFFFFAA.toInt(),
)