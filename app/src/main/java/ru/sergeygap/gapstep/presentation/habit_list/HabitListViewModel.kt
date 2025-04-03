package ru.sergeygap.gapstep.presentation.habit_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sergeygap.gapstep.data.HabitRepositoryIml
import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.entity.SortType
import ru.sergeygap.gapstep.domain.usecase.DeleteHabitUseCase
import ru.sergeygap.gapstep.domain.usecase.GetListHabitUseCase
import ru.sergeygap.gapstep.domain.usecase.GetSearchListHabitUseCase
import ru.sergeygap.gapstep.domain.usecase.IncreaseCountInHabitUseCase
import ru.sergeygap.gapstep.domain.usecase.SetSearchQueryUseCase

class HabitListViewModel : ViewModel() {

    private val repository = HabitRepositoryIml
    private val getListHabitUseCase = GetListHabitUseCase(repository)
    private val deleteHabitUseCase = DeleteHabitUseCase(repository)
    private val increaseCountInHabitUseCase = IncreaseCountInHabitUseCase(repository)
    private val getSearchListHabitUseCase = GetSearchListHabitUseCase(repository)
    private val setSearchQueryUseCase = SetSearchQueryUseCase(repository)

    private val _habits = MutableLiveData(getListHabitUseCase())
    val habits: LiveData<List<Habit>> = _habits

    var sortType: SortType = SortType.NORMAL
    private val _habitsSearch = MutableLiveData(getSearchListHabitUseCase())
    val habitsSearch: LiveData<List<Habit>> = _habitsSearch

    fun deleteHabit(habit: Habit) {
        deleteHabitUseCase(habit)
        _habits.value = getListHabitUseCase()
    }

    fun increaseCountInHabit(habit: Habit) {
        increaseCountInHabitUseCase(habit)
        _habits.value = getListHabitUseCase()
    }

    fun setSearchQuery(searchText: String) {
        setSearchQueryUseCase(searchText, sortType)
        _habitsSearch.value = getSearchListHabitUseCase()
    }

    fun updateSearchResult() {
        _habitsSearch.value = getSearchListHabitUseCase()
    }
}