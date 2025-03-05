package ru.sergeygap.gapstep.presentation.habit_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sergeygap.gapstep.data.HabitRepositoryIml
import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.usecase.GetListHabitUseCase

class HabitListViewModel : ViewModel() {

    private val repository = HabitRepositoryIml
    private val getListHabitUseCase = GetListHabitUseCase(repository)

    private val _habits = MutableLiveData<List<Habit>>(getListHabitUseCase())
    val habits: LiveData<List<Habit>> = _habits



    fun increaseItemCount(habit: Habit) {

    }
    fun decreaseItemCount(habit: Habit) {

    }


}