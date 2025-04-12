package ru.sergeygap.gapstep.domain.usecase

import ru.sergeygap.gapstep.domain.repository.HabitRepository

class GetSearchListHabitUseCase(
    private val repository: HabitRepository,
) {
    operator fun invoke() = repository.getSearchListHabit()
}
