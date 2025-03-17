package ru.sergeygap.gapstep.domain.entity

sealed class HabitType(
    val type: String,
) {

    data object Useful : HabitType("Полезная")

    data object NotUseful : HabitType("Неполезная")
}