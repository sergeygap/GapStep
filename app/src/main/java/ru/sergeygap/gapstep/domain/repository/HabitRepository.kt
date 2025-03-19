package ru.sergeygap.gapstep.domain.repository

import ru.sergeygap.gapstep.domain.entity.Habit

interface HabitRepository {

    fun getHabitById(id: Int): Habit

    fun getListHabit(): List<Habit>

    fun updateHabit(habit: Habit)

    fun addHabit(habit: Habit)

    fun deleteHabit(habit: Habit)

    fun increaseCountInHabit(habit: Habit)
}
