package ru.sergeygap.gapstep.domain.usecase

import ru.sergeygap.gapstep.domain.repository.HabitRepository

class GetListHabitUseCase(
    private val repository: HabitRepository,
) {
    operator fun invoke() = repository.getListHabit()
}