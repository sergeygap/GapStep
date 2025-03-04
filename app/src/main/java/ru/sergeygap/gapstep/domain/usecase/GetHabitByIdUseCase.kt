package ru.sergeygap.gapstep.domain.usecase

import ru.sergeygap.gapstep.domain.repository.HabitRepository

class GetHabitByIdUseCase(
    private val repository: HabitRepository,
) {
    operator fun invoke(id: Int) = repository.getHabitById(id)
}