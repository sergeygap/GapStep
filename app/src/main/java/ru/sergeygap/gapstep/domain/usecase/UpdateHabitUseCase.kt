package ru.sergeygap.gapstep.domain.usecase

import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.repository.HabitRepository

class UpdateHabitUseCase(
    private val repository: HabitRepository,
) {
    operator fun invoke(habit: Habit) = repository.updateHabit(habit)
}