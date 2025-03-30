package ru.sergeygap.gapstep.data

import ru.sergeygap.gapstep.data.dto.HabitDto
import ru.sergeygap.gapstep.data.mapper.Mapper
import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.entity.HabitType
import ru.sergeygap.gapstep.domain.repository.HabitRepository

object HabitRepositoryIml : HabitRepository {

    private val _listHabits = mutableListOf<HabitDto>().apply {
        repeat(20) {
            this.add(
                HabitDto(
                    id = it,
                    type = if (it % 2 == 0) HabitType.Useful else HabitType.NotUseful,
                )
            )
        }
    }

    override fun getHabitById(id: Int): Habit {
        return _listHabits.find { it.id == id }?.let { Mapper.mapDtoToEntity(it) }
            ?: throw IllegalArgumentException("Habit with id $id not found")
    }

    override fun getListHabit(): List<Habit> = _listHabits.map { Mapper.mapDtoToEntity(it) }

    override fun addHabit(habit: Habit) {
        val newId = if (habit.id == -1) {
            (_listHabits.maxByOrNull { it.id }?.id ?: 0) + 1
        } else {
            habit.id
        }
        _listHabits.add(Mapper.mapEntityToDto(habit.copy(id = newId)))
    }

    override fun updateHabit(habit: Habit) {
        val index = _listHabits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            _listHabits[index] = Mapper.mapEntityToDto(habit)
        } else {
            throw IllegalArgumentException("Habit with id ${habit.id} not found")
        }
    }

    override fun deleteHabit(habit: Habit) {
        _listHabits.removeIf { it.id == habit.id }
    }

    override fun increaseCountInHabit(habit: Habit) {
        val index = _listHabits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            _listHabits[index] = Mapper.mapEntityToDto(habit.copy(count = habit.count + 1))
        } else {
            throw IllegalArgumentException("Habit with id ${habit.id} not found")
        }
    }
}
