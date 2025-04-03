package ru.sergeygap.gapstep.domain.usecase

import ru.sergeygap.gapstep.domain.entity.SortType
import ru.sergeygap.gapstep.domain.repository.HabitRepository

class SetSearchQueryUseCase(
    private val repository: HabitRepository,
) {
    operator fun invoke(searchText: String, sortType: SortType) =
        repository.setSearchQueryUseCase(searchText, sortType)
}
