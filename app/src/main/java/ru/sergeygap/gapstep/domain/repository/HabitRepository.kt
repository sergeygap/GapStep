package ru.sergeygap.gapstep.domain.repository

import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.entity.SortType

interface HabitRepository {

    fun getHabitById(id: Int): Habit

    fun getListHabit(): List<Habit>

    fun updateHabit(habit: Habit)

    fun addHabit(habit: Habit)

    fun deleteHabit(habit: Habit)

    fun increaseCountInHabit(habit: Habit)

    fun getSearchListHabit(): List<Habit>

    fun setSearchQuery(searchText: String, sortType: SortType)
}
