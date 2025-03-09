package ru.sergeygap.gapstep.data

import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.repository.HabitRepository

object HabitRepositoryIml : HabitRepository {

    private val _listHabits = mutableListOf<Habit>()

    override fun getHabitById(id: Int): Habit {
        return _listHabits.find { it.id == id }
            ?: throw IllegalArgumentException("Habit with id $id not found")
    }

    override fun getListHabit(): List<Habit> = _listHabits.toList()

    override fun addHabit(habit: Habit) {
        val newId = if (habit.id == -1) {
            (_listHabits.maxByOrNull { it.id }?.id ?: 0) + 1
        } else {
            habit.id
        }
        _listHabits.add(habit.copy(id = newId))
    }

    override fun updateHabit(habit: Habit) {
        val index = _listHabits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            _listHabits[index] = habit
        } else {
            throw IllegalArgumentException("Habit with id ${habit.id} not found")
        }
    }

    override fun deleteHabit(habit: Habit) {
        _listHabits.removeIf { it.id == habit.id }
    }
}
