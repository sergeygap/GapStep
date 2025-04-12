package ru.sergeygap.gapstep.domain.entity

data class Habit(
    val id: Int,
    val name: String,
    val createdDate: String,
    val description: String,
    val priority: String,
    val type: HabitType,
    val count: Int,
    val period: Int,
    val color: Int,
) {
    companion object {
        val Dummy = Habit(
            id = 0,
            name = "Чтение",
            createdDate = "12.04.2025",
            description = "Чтение книг по вечерам",
            priority = "Важная",
            type = HabitType.Useful,
            count = 0,
            period = 0,
            color = 0xFFFFFFAA.toInt(),
        )
    }
}
