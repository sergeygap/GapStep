package ru.sergeygap.gapstep.presentation.habit_create

import androidx.lifecycle.ViewModel
import ru.sergeygap.gapstep.data.HabitRepositoryIml
import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.usecase.AddHabitUseCase
import ru.sergeygap.gapstep.domain.usecase.DeleteHabitUseCase
import ru.sergeygap.gapstep.domain.usecase.GetHabitByIdUseCase
import ru.sergeygap.gapstep.domain.usecase.UpdateHabitUseCase

class CreateHabitViewModel : ViewModel() {

    private val repository = HabitRepositoryIml
    private val addHabitUseCase = AddHabitUseCase(repository)
    private val deleteHabitUseCase = DeleteHabitUseCase(repository)
    private val updateHabitUseCase = UpdateHabitUseCase(repository)
    private val getHabitByIdUseCase = GetHabitByIdUseCase(repository)

    fun addHabit(habit: Habit) {
        addHabitUseCase(habit)
    }

    fun deleteHabit(habit: Habit) {
        deleteHabitUseCase(habit)
    }

    fun updateHabit(habit: Habit) {
        updateHabitUseCase(habit)
    }

    fun getHabitById(id: Int): Habit {
        return getHabitByIdUseCase(id)
    }

}
