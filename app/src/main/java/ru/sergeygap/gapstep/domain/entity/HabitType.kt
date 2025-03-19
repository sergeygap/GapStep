package ru.sergeygap.gapstep.domain.entity

sealed class HabitType(
    val type: String,
) {

    data object Useful : HabitType("Полезная")

    data object NotUseful : HabitType("Неполезная")

    companion object {
        fun from(value: String): HabitType? = when (value) {
            Useful.type -> Useful
            NotUseful.type -> NotUseful
            else -> null
        }
    }
}