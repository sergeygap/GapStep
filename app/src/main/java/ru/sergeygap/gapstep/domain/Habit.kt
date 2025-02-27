package ru.sergeygap.gapstep.domain

import androidx.annotation.ColorInt

data class Habit(
    val name: String = "Чтение",
    val description: String = "Чтение книг по вечерам",
    val priority: String = "Важная",
    val type: String = "Полезная",
    val count: String = "0",
    @ColorInt
    val color: Int = 0xFFFFFFAA.toInt(),
)
