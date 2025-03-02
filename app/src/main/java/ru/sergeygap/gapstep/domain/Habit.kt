package ru.sergeygap.gapstep.domain

import androidx.annotation.ColorInt

data class Habit(
    val id: Int = 0,
    val name: String = "Чтение",
    val description: String = "Чтение книг по вечерам",
    val priority: String = "Важная",
    val type: String = "Полезная",
    val count: Int = 0,
    val period: Int = 0,
    @ColorInt
    val color: Int = 0xFFFFFFAA.toInt(),
)
