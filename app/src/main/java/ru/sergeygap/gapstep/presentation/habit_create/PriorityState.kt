package ru.sergeygap.gapstep.presentation.habit_create

sealed class PriorityState(val label: String) {

    object Low : PriorityState("Низкий")
    object Medium : PriorityState("Средний")
    object High : PriorityState("Низкий")

    override fun toString() = label
}