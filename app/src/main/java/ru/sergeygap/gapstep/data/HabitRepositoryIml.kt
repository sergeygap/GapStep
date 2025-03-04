package ru.sergeygap.gapstep.data

import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.repository.HabitRepository

object HabitRepositoryIml : HabitRepository {

    override fun getHabitById(id: Int): Habit {
        TODO("Not yet implemented")
    }

    override fun getListHabit(): List<Habit> {
        TODO("Not yet implemented")
    }

    override fun updateHabit(habit: Habit) {
        TODO("Not yet implemented")
    }

    override fun addHabit(habit: Habit) {
        TODO("Not yet implemented")
    }

    override fun deleteHabit(habit: Habit) {
        TODO("Not yet implemented")
    }
}